package crud2.Crud2.service;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class GCSService {

    public void uploadFile(String bucketName, String objectName, MultipartFile file) throws IOException {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        storage.create(
                BlobInfo.newBuilder(bucketName, objectName).build(),
                file.getBytes()
        );
    }
}
