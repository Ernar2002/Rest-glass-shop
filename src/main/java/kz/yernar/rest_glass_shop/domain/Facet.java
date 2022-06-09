package kz.yernar.rest_glass_shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "facet")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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

    @OneToMany(mappedBy = "facet", cascade = CascadeType.ALL)
    private List<Product> productList;
}
