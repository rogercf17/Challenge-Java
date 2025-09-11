package br.com.dasa.estoque.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateParsing {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private DateParsing() {}

    public static LocalDateTime parseDateTime(String data) {
        return LocalDateTime.parse(data, formatter);
    }
}
