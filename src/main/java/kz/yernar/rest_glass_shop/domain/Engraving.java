package kz.yernar.rest_glass_shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "engraving", cascade = CascadeType.ALL)
    private List<Product> productList;
}
