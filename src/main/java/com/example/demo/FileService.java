package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final Resource resourceFile;

    public FileService(@Value("classpath:marijuana-street-price-clean.csv") Resource resourceFile) {
        this.resourceFile = resourceFile;
    }

    public String fileContent() throws IOException {
        return Files.readString(resourceFile.getFile().toPath());
    }

    public List<PricePerDay> readPrices() throws IOException {
        List<String> strings = Files.readAllLines(resourceFile.getFile().toPath());
        return strings.stream()
                .filter(Objects::nonNull)
                .map(line -> line.split(","))
                .map(table -> new PricePerDay(
                        table[0],
                        getPriceBigDecimal(table[1]),
                        getPriceBigDecimal(table[3]),
                        getPriceBigDecimal(table[5]),
                        getDate(table[7])
                ))
                .collect(Collectors.toList());

    }

    private BigDecimal getPriceBigDecimal(String s) {
        if (s == null){
            return null;
        }
        try {
            return new BigDecimal(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private LocalDate getDate(String s) {
        if (s == null) {
            return null;
        }
        return  LocalDate.parse(s);
    }
}
