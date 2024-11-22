package io.sounds.controller.model;

import lombok.Data;

@Data
public class SongMetadata {
    private Long id;
    private String name;
    private String artist;
    private String album;
    private String duration;
    private String year;
}
