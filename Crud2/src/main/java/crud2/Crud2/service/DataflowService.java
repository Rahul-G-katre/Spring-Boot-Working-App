package crud2.Crud2.service;

import crud2.Crud2.pipeline.MyDataflowPipeline;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DataflowService {

    @Value("e-commerce-app-rahul")
    private String projectId;

    public void runPipeline(String bucketName, String objectName, String dataset, String table) {
        String inputFilePath = "gs://" + bucketName + "/" + objectName;
        MyDataflowPipeline.run(inputFilePath, projectId, dataset, table);
    }
}
