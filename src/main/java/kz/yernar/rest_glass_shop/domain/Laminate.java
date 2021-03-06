package kz.yernar.rest_glass_shop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "laminate")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Laminate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private double cost;

    @OneToOne(mappedBy = "laminate")
//    @JsonBackReference
    @JsonIgnore
    private Order order;
}
