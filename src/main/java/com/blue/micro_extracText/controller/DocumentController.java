package com.blue.micro_extracText.controller;

import com.blue.micro_extracText.model.ApiResponse;
import com.blue.micro_extracText.model.DocumentResponse;
import com.blue.micro_extracText.service.DocumentTextExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentTextExtractorService extractorService;


    @PostMapping
    public ResponseEntity<ApiResponse<DocumentResponse>>  extractText(@RequestParam("file") MultipartFile file) {
        return extractorService.extractResult(file);
    }

}
