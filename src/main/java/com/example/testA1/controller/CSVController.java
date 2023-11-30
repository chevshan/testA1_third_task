package com.example.testA1.controller;

import com.example.testA1.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class CSVController {
    private final CSVService csvService;

    @Autowired
    public CSVController(CSVService CSVService) {
        this.csvService = CSVService;
    }

    @PostMapping("/upload-csv-file")
    public String uploadLoginsFile(@RequestParam("fileLogins") MultipartFile fileLogins,
                                   @RequestParam("filePostings") MultipartFile filePostings) {

        csvService.save(fileLogins, filePostings);

        return "OK";
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
        File tempFile = csvService.downloadPostings();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(tempFile));

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment;filename=postings.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .contentLength(tempFile.length())
                .body(resource);
    }
}
