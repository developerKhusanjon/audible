package io.sounds.service.impl;

import io.sounds.service.ResourceMetadata;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ResourceMetadataImpl implements ResourceMetadata {
    @Override
    public Long storeMetadata(File file) {
        return 0L;
    }
}
