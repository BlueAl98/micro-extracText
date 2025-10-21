package com.blue.micro_extracText.service;

import com.blue.micro_extracText.model.ApiResponse;
import com.blue.micro_extracText.model.DocumentResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DocumentTextExtractorService {


    public ResponseEntity<ApiResponse<DocumentResponse>> extractResult(MultipartFile file) {
        try {
            String text = extractText(file);

            DocumentResponse response = new DocumentResponse(
                    file.getOriginalFilename(),
                    text,
                    file.getSize()
            );

            return ResponseEntity.ok(
                    new ApiResponse<>(HttpStatus.OK.value(), "Text extracted successfully", response)
            );

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(
                            HttpStatus.BAD_REQUEST.value(),
                            "Failed to extract text",
                            e.getMessage()
                    ));
        }
    }


    private String extractText(MultipartFile file) throws Exception {
        String name = file.getOriginalFilename().toLowerCase();

        if (name.endsWith(".txt")) {
            return new String(file.getBytes());
        }

        if (name.endsWith(".docx")) {
            try (InputStream is = file.getInputStream();
                 XWPFDocument doc = new XWPFDocument(is);
                 XWPFWordExtractor extractor = new XWPFWordExtractor(doc)) {
                return extractor.getText();
            }
        }

        if (name.endsWith(".pdf")) {
            try (InputStream is = file.getInputStream();
                 PDDocument document = PDDocument.load(is)) {
                PDFTextStripper stripper = new PDFTextStripper();
                return stripper.getText(document);
            }
        }

        throw new IllegalArgumentException("Unsupported file type. Allowed: .txt, .docx, .pdf");
    }


    private String extractFromTxt(File file) throws IOException {
        return new String(java.nio.file.Files.readAllBytes(file.toPath()));
    }

    private String extractFromPdf(File file) throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    private String extractFromDocx(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument doc = new XWPFDocument(fis);
             XWPFWordExtractor extractor = new XWPFWordExtractor(doc)) {
            return extractor.getText();
        }
    }


}
