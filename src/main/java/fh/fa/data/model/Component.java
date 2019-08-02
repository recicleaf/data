package fh.fa.data.model;

import fh.fa.data.repository.DatabaseEntity;
import fh.fa.data.repository.converters.LongListToJsonConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "components")
public class Component implements DatabaseEntity {

    private static final long serialVersionUID = -2343243243246732343L;

    @Id
    private Long id;
    private String name;
    @Column(name = "material_ids")
    @Convert(converter = LongListToJsonConverter.class)
    private List<Long> materialIds;

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
}
