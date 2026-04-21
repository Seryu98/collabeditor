package com.example.collabeditor.document.service;

import com.example.collabeditor.document.dto.request.DocumentCreateRequest;
import com.example.collabeditor.document.dto.request.DocumentSaveRequest;
import com.example.collabeditor.document.dto.response.DocumentResponse;
import com.example.collabeditor.document.entity.Document;
import com.example.collabeditor.document.repository.DocumentRepository;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Transactional
    public DocumentResponse createDocument(DocumentCreateRequest request) {
        Document document = new Document(request.getTitle(), "");
        Document saved = documentRepository.save(document);
        return DocumentResponse.from(saved);
    }

    public DocumentResponse getDocument(Long documentId) {
        Document document = findDocumentOrThrow(documentId);
        return DocumentResponse.from(document);
    }

    @Transactional
    public DocumentResponse saveDocument(Long documentId, DocumentSaveRequest request) {
        Document document = findDocumentOrThrow(documentId);
        document.updateContent(request.getContent());
        return DocumentResponse.from(document);
    }

    private Document findDocumentOrThrow(Long documentId) {
        Objects.requireNonNull(documentId, "documentId must not be null");
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new NoSuchElementException("문서를 찾을 수 없습니다. id=" + documentId));
    }
}
