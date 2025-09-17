package br.com.dasa.estoque.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateParsing {

    // formatos com data + hora (mais específicos primeiro)
    private static final DateTimeFormatter[] DATETIME_FORMATTERS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    };

    // formatos só com data
    private static final DateTimeFormatter[] DATE_FORMATTERS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd")
    };

    // formato padrão de saída (para formatDateTime)
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateParsing() {}

    public static LocalDateTime parseDateTime(String data) {
        if (data == null) {
            throw new DateTimeParseException("Data nula", "", 0);
        }

        // normaliza o texto: trim, NBSP -> espaço, T -> espaço, / -> -, colapsa múltiplos espaços
        String s = data.trim()
                .replace('\u00A0', ' ')
                .replace('T', ' ')
                .replace('/', '-')
                .replaceAll("\\s+", " ");

        // 1) tenta formatos com data+hora
        for (DateTimeFormatter fmt : DATETIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(s, fmt);
            } catch (DateTimeParseException ignored) {
                // tentar próximo formato
            }
        }

        // 2) tenta formatos só data -> converte para início do dia
        for (DateTimeFormatter fmt : DATE_FORMATTERS) {
            try {
                LocalDate ld = LocalDate.parse(s, fmt);
                return ld.atStartOfDay();
            } catch (DateTimeParseException ignored) {
                // tentar próximo formato
            }
        }

        // Se chegou aqui, nenhum formato casou -> mensagem útil
        throw new DateTimeParseException(
                "Formato inválido. Formatos aceitos ex.: 'yyyy-MM-dd HH:mm:ss', 'yyyy-MM-ddTHH:mm:ss', 'dd/MM/yyyy', 'yyyy/MM/dd'.",
                s, 0
        );
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return "N/A";
        return dateTime.format(OUTPUT);
    }
}
