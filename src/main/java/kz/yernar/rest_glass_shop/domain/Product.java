package kz.yernar.rest_glass_shop.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double width;

    @Column(nullable = false)
    private double height;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category = new Category();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "facet_id")
    private Facet facet;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cutout_id")
    private Cutout cutout;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "engraving_id")
    private Engraving engraving;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "edge_treatment_id")
    private EdgeTreatment edgeTreatment = new EdgeTreatment();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hole_id")
    private Hole hole;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "drawing_id")
    private Drawing drawing;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "laminate_id")
    private Laminate laminate;
}
