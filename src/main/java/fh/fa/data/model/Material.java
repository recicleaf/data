package fh.fa.data.model;

import fh.fa.data.repository.converters.ListToJsonConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "materials")
public class Material implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;

    @Id
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "abbreviations")
    @Convert(converter = ListToJsonConverter.class)
    public List<String> abbreviations;

    @Column(name = "images")
    @Convert(converter = ListToJsonConverter.class)
    public String[] images;

    @Column(name = "type")
    public String type;
}
