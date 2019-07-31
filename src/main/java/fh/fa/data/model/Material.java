package fh.fa.data.model;

import fh.fa.data.repository.DatabaseEntity;
import fh.fa.data.repository.converters.ListToJsonConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "materials")
public class Material implements DatabaseEntity {

    private static final long serialVersionUID = -2343243243242432341L;

    @Id
    private Long id;
    private String name;
    @Convert(converter = ListToJsonConverter.class)
    private List<String> abbreviations;
    @Convert(converter = ListToJsonConverter.class)
    private List<String> images;
    private String type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getAbbreviations() {
        return abbreviations;
    }

    public void setAbbreviations(final List<String> abbreviations) {
        this.abbreviations = abbreviations;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(final List<String> images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}
