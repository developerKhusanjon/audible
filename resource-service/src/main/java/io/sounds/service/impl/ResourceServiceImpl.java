package io.sounds.service.impl;

import io.sounds.controller.model.UploadedMP3;
import io.sounds.controller.model.status.UploadStatus;
import io.sounds.repository.ResourceRepository;
import io.sounds.repository.domain.Resource;
import io.sounds.service.ResourceMetadata;
import io.sounds.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceMetadata resourceMetadata;
    private final ResourceRepository repository;

    @Override
    public Pair<UploadedMP3, UploadStatus> uploadMP3(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return Pair.of(new UploadedMP3(), UploadStatus.EMPTY_FILE);
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = multipartFile.getBytes();
            File uploadedFile = new File(System.getProperty("user.dir") + "/upload" + multipartFile.getOriginalFilename());
            multipartFile.transferTo(uploadedFile);
            Resource resourceRaw = Resource.builder()
                    .metaDataId(resourceMetadata.storeMetadata(uploadedFile))
                    .filePath(uploadedFile.getAbsolutePath())
                    .path("/download/" + uploadedFile.getName())
                    .build();
            Resource resource = repository.save(resourceRaw);

            return Pair.of(new UploadedMP3(resource.getId()), UploadStatus.UPLOADED);
        } catch (IOException e) {
            return Pair.of(new UploadedMP3(), UploadStatus.UPLOAD_ERROR);
        }
    }
}
