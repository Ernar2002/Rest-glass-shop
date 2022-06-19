package kz.yernar.rest_glass_shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "edge_treatment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EdgeTreatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private double cost;

    @OneToMany(mappedBy = "edgeTreatment", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> productSet = new HashSet<>();
}
