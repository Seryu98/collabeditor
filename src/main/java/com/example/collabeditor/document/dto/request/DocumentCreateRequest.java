package com.example.collabeditor.document.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DocumentCreateRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    public String getTitle() {
        return title;
    }
}
