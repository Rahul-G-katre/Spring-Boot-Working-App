package crud2.Crud2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/pipeline")
public class PipelineController {

    @Autowired private crud2.Crud2.service.GCSService gcsService;
    @Autowired private crud2.Crud2.service.BigQueryService bqService;
    @Autowired private crud2.Crud2.service.DataflowService dataflowService;

    @PostMapping("/run")
    public ResponseEntity<String> runPipeline(@RequestParam("file") MultipartFile file) throws IOException {
        String bucketName = "cllgstudataforbigquerydataflow";
        String objectName = "indian_employees_cleaned.csv";
        String dataset = "dataflowDemo";
        String table = "Employee_Table";

        gcsService.uploadFile(bucketName, objectName, file);
        bqService.createTable(dataset, table);
        dataflowService.runPipeline(bucketName, objectName, dataset, table);

        return ResponseEntity.ok("Dataflow pipeline started successfully.");
    }
}
