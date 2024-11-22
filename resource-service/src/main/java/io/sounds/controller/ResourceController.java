package io.sounds.controller;

import io.sounds.controller.model.UploadedMP3;
import io.sounds.controller.model.status.UploadStatus;
import io.sounds.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<UploadedMP3> uploadMP3(@RequestParam("file") MultipartFile file) {
        Pair<UploadedMP3, UploadStatus> result = resourceService.uploadMP3(file);

        switch (result.getSecond()) {
            case UPLOADED -> ResponseEntity.status(HttpStatus.OK).body(result.getFirst());
            case EMPTY_FILE -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UploadedMP3());
            case UPLOAD_ERROR -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UploadedMP3(-1L));
        }

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new UploadedMP3(0L));
    }
}
