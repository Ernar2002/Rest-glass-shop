package kz.yernar.rest_glass_shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "hole", cascade = CascadeType.ALL)
    private List<Product> productList;
}
