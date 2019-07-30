package fh.fa.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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

//    @Column(name = "abbreviations")
//    public String[] abbreviations;
//
//    @Column(name = "imgs")
//    public String[] imgs;

    @Column(name = "type")
    public String type;
}
