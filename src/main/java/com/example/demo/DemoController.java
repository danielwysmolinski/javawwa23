package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class DemoController {

    private final FileService fileService;

    @Autowired
    public DemoController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public String fileContent() throws IOException {
        return fileService.fileContent();
    }

    @GetMapping("/stateprice")
    public List<PricePerDay> priceContent() throws IOException {
        return fileService.readPrices();
    }

}