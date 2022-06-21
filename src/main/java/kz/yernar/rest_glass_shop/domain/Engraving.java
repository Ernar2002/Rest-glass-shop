package kz.yernar.rest_glass_shop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "engraving")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Engraving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int meter;

    @Column(nullable = false)
    private double cost;

    @OneToOne(mappedBy = "engraving")
    @JsonBackReference
    private Product product;
}
