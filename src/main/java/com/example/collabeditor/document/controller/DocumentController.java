package com.example.collabeditor.document.controller;

import com.example.collabeditor.document.dto.request.DocumentCreateRequest;
import com.example.collabeditor.document.dto.request.DocumentSaveRequest;
import com.example.collabeditor.document.dto.response.DocumentResponse;
import com.example.collabeditor.document.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public DocumentResponse createDocument(@Valid @RequestBody DocumentCreateRequest request) {
        return documentService.createDocument(request);
    }

    @GetMapping("/{id}")
    public DocumentResponse getDocument(@PathVariable("id") Long documentId) {
        return documentService.getDocument(documentId);
    }

    @PutMapping("/{id}")
    public DocumentResponse saveDocument(
            @PathVariable("id") Long documentId,
            @Valid @RequestBody DocumentSaveRequest request
    ) {
        return documentService.saveDocument(documentId, request);
    }
}
