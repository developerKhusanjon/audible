package io.sounds.service;

import io.sounds.controller.model.SongMetadata;
import io.sounds.controller.model.UploadedMeta;

public interface SongService {
    UploadedMeta storeMeta(SongMetadata metadata);
}
