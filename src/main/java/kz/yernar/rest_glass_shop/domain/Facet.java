package kz.yernar.rest_glass_shop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "facet")
@Getter @Setter
@NoArgsConstructor
public class Facet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_side")
    private double firstSide;

    @Column(name = "second_side")
    private double secondSide;

    @Column(nullable = false)
    private double cost;

    @OneToOne(mappedBy = "facet")
    @JsonIgnore
    private Order order;
}
