package com.example.collabeditor.document.repository;

import com.example.collabeditor.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
