package com.example.collabeditor.document.dto.request;

import jakarta.validation.constraints.NotNull;

public class DocumentSaveRequest {

    @NotNull
    private String content;

    public String getContent() {
        return content;
    }
}
