package com.ajrat.application;

import com.ajrat.domain.Item;
import com.ajrat.domain.PurchaseOrder;
import com.ajrat.file.utils.ApacheFUDemo;
import com.ajrat.file.utils.SpringFUDemo;
import com.ajrat.xml.parsers.DOMparser;
import com.ajrat.xml.parsers.JAXB;
import com.ajrat.xml.parsers.StAXparser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;

@Slf4j
public class Main {
    public static void logItem(Item item) {
        log.info(String.valueOf(item));
    }

    public static void runParsers(File file) {
        DOMparser.parse(file);
        StAXparser.parse(file);
    }

    public static void runStreamOperations(PurchaseOrder purchaseOrder) {
        log.info("===== Original items ==========");
        purchaseOrder.items
                .forEach(Main::logItem);
        log.info("===== Sort by price ==========");
        purchaseOrder.items.stream()
                .sorted((a, b) -> (int) (a.price - b.price))
                .forEach(Main::logItem);
        log.info("===== Filter by price gt 100 ==========");
        purchaseOrder.items.stream()
                .filter((x) -> x.price > 100.0)
                .forEach(Main::logItem);
        log.info("===== If price gt 100 set qty to 5 (map)===========");
        purchaseOrder.items.stream()
                .filter((x) -> x.price > 100.0)
                .map(x -> {
                    x.quantity = 5;
                    return x;
                })
                .forEach(Main::logItem);

    }

    public static void main(String[] args) {
        File file = FileUtils.getFile(Main.class.getClassLoader().getResource("order.xml").getPath());
        SpringFUDemo springFUDemo = new SpringFUDemo();
        JAXB jaxb = new JAXB();

        ApacheFUDemo.demo();
        springFUDemo.logHash(file);
        runParsers(file);
        PurchaseOrder purchaseOrder = jaxb.getPurchaseOrder(file);
        jaxb.getXML(purchaseOrder);
        runStreamOperations(purchaseOrder);
    }
}
