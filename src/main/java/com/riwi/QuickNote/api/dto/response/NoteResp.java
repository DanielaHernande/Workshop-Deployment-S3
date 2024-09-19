package com.riwi.QuickNote.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteResp {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateCreation;
};