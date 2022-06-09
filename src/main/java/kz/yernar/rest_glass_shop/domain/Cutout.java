package kz.yernar.rest_glass_shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cutout")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Cutout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inside_count")
    private double insideCount;

    @Column(name = "outside_count")
    private double outsideCount;

    @Column(nullable = false)
    private double cost;

    @OneToMany(mappedBy = "cutout", cascade = CascadeType.ALL)
    private List<Product> productList;
}
