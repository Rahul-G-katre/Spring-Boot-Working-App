package crud2.Crud2.pipeline;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableReference;
import com.google.api.services.bigquery.model.TableRow;
import com.google.api.services.bigquery.model.TableSchema;
import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.DoFn.Element;
import org.apache.beam.sdk.transforms.DoFn.OutputReceiver;

import java.util.Arrays;

public class MyDataflowPipeline {

    public static void run(String inputFilePath, String projectId, String datasetId, String tableId) {
        DataflowPipelineOptions options = PipelineOptionsFactory.as(DataflowPipelineOptions.class);
        options.setProject(projectId);
        options.setRegion("us-central1"); // ✅ You can change the region here
        options.setTempLocation("gs://cllgstudataforbigquerydataflow/tmp"); // ✅ Ensure /tmp path exists
        options.setRunner(DataflowRunner.class);
        options.setJobName("my-dataflow-job-" + System.currentTimeMillis());

        Pipeline pipeline = Pipeline.create(options);

        TableReference bqTable = new TableReference()
                .setProjectId(projectId)
                .setDatasetId(datasetId)
                .setTableId(tableId);

        pipeline
                .apply("ReadFromGCS", TextIO.read().from(inputFilePath))
                .apply("ParseCSV", ParDo.of(new DoFn<String, TableRow>() {
                    @ProcessElement
                    public void processElement(@Element String line, OutputReceiver<TableRow> out) {
                        // Skip header row
                        if (line.toLowerCase().startsWith("location")) {
                            return;
                        }

                        String[] parts = line.split(",", -1); // -1 to handle empty values
                        if (parts.length == 5) {
                            TableRow row = new TableRow()
                                    .set("location", parts[0].trim())
                                    .set("name", parts[1].trim())
                                    .set("age", parts[2].trim())
                                    .set("color", parts[3].trim())
                                    .set("coffee", parts[4].trim());
                            out.output(row);
                        }
                    }
                }))
                .apply("WriteToBigQuery", BigQueryIO.writeTableRows()
                        .to(bqTable)
                        .withSchema(new TableSchema().setFields(Arrays.asList(
                                new TableFieldSchema().setName("location").setType("STRING"),
                                new TableFieldSchema().setName("name").setType("STRING"),
                                new TableFieldSchema().setName("age").setType("STRING"),
                                new TableFieldSchema().setName("color").setType("STRING"),
                                new TableFieldSchema().setName("coffee").setType("STRING")
                        )))
                        .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND)
                        .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED)
                );

        pipeline.run().waitUntilFinish();
    }
}
