package com.ajrat.file.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

@Slf4j
public class ApacheFUDemo {
    public static void demo() {
        log.info("===== TEMP directory is {}", FileUtils.getTempDirectoryPath());
    }
}
