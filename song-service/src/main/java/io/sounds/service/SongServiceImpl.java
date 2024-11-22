package io.sounds.service;

import io.sounds.controller.model.SongMetadata;
import io.sounds.controller.model.UploadedMeta;
import io.sounds.repository.SongRepository;
import io.sounds.repository.domain.SongMeta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    @Override
    public UploadedMeta storeMeta(SongMetadata metadata) {
        SongMeta songMeta = songRepository.save(SongMeta.builder()
                        .name(metadata.getName())
                        .artist(metadata.getArtist())
                        .album(metadata.getAlbum())
                        .year(metadata.getYear())
                        .duration(metadata.getDuration())
                .build());

        return new UploadedMeta(songMeta.getId());
    }
}
