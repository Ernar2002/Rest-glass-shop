package kz.yernar.rest_glass_shop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drawing")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Drawing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private double cost;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL)
    private List<Product> productList;
}
