package com.pharmacy.controller;

import com.pharmacy.masterdata.MasterData;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/masterData")
@AllArgsConstructor
public class MasterDataController {
    private final MasterData data;

    @PostMapping
    public ResponseEntity<?> masterData() {
        data.masterData();
        return ResponseEntity.ok("saved");
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        data.store(file);
        return ResponseEntity.ok("uploaded");
    }
}
