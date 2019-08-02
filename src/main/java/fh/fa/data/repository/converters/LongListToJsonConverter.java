package fh.fa.data.repository.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LongListToJsonConverter implements AttributeConverter<List<Long>, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(final List<Long> values) {
        try {
            return objectMapper.writeValueAsString(values);
        } catch (final JsonProcessingException e) {
        }
        return "";
    }

    @Override
    public List<Long> convertToEntityAttribute(final String s) {
        try {
            if (s == null || s.equals("null")) {
                return List.of();
            } else {
                return (List<Long>) objectMapper.readValue(s, List.class).stream()
                                                .map(o -> Long.valueOf(o.toString()))
                                                .collect(Collectors.toList());
            }
        } catch (final IOException e) {
        }
        return List.of();
    }
}
