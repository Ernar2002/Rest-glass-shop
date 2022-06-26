package kz.yernar.rest_glass_shop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "drawing")
//    @JsonBackReference
    @JsonIgnore
    private Order order;
}
