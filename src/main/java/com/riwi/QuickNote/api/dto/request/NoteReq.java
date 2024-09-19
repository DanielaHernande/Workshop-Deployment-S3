package com.riwi.QuickNote.api.dto.request;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteReq {
    
    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "The title must be between 1 to 200 characters long")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 1, max = 200, message = "The content must have at least 1 character")
    private String content;

    @CreationTimestamp
    private LocalDateTime dateCreation;
}