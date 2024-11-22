package io.sounds.service;

import io.sounds.controller.model.UploadedMP3;
import io.sounds.controller.model.status.UploadStatus;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

public interface ResourceService {
    Pair<UploadedMP3, UploadStatus> uploadMP3(MultipartFile file);
}
