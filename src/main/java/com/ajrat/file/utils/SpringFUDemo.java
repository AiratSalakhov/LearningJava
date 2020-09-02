package com.ajrat.file.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class SpringFUDemo {
    public Optional<String> getHash(File file) {
        Optional<String> hash;
        try {
            hash = Optional.of(org.springframework.boot.loader.tools.FileUtils.sha1Hash(file));
        } catch (IOException e) {
            log.info("!!! ===== getHash IOException {}", e.getMessage());
            return Optional.empty();
        }
        return hash.or(Optional::empty);
    }

    public void logHash(File file) {
        getHash(file).ifPresent((x) -> {
            log.info("===== Processing file {}", file.getAbsolutePath());
            log.info("===== Hash of file {} is: {}", file.getName(), x);
        });
    }
}
