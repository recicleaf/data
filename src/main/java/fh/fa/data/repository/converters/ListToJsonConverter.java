package fh.fa.data.repository.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;

public class ListToJsonConverter implements AttributeConverter<List<String>, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(final List<String> strings) {
        try {
            return objectMapper.writeValueAsString(strings);
        } catch (final JsonProcessingException e) {
        }
        return "";
    }

    @Override
    public List<String> convertToEntityAttribute(final String s) {
        try {
            return objectMapper.readValue(s, List.class);
        } catch (final IOException e) {
        }
        return List.of();
    }
}
