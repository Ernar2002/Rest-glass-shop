package kz.yernar.rest_glass_shop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hole")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Hole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double diameter;

    private int count;

    @Column(nullable = false)
    private double cost;

    @OneToOne(mappedBy = "hole")
    @JsonBackReference
    private Product product;
}
