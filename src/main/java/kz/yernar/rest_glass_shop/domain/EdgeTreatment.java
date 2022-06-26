package kz.yernar.rest_glass_shop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "edgeTreatment")
//    @JsonBackReference
    @JsonIgnore
    private Order order;
}
