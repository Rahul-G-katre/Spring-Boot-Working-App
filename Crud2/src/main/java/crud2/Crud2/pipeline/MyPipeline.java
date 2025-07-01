package crud2.Crud2.pipeline;
import org.apache.beam.sdk.Pipeline;

public class MyPipeline {
    public static void runPipeline() {
        Pipeline pipeline = Pipeline.create();
        // define transforms here
        pipeline.run().waitUntilFinish();
    }
}
