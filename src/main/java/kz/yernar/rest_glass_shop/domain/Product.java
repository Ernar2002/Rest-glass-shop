package kz.yernar.rest_glass_shop.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
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
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "facet_id", referencedColumnName = "id")
    @JsonManagedReference
    private Facet facet;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cutout_id", referencedColumnName = "id")
    private Cutout cutout;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "engraving_id", referencedColumnName = "id")
    private Engraving engraving;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "edge_treatment_id", referencedColumnName = "id")
    private EdgeTreatment edgeTreatment;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hole_id", referencedColumnName = "id")
    private Hole hole;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "drawing_id", referencedColumnName = "id")
    private Drawing drawing = new Drawing();

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "laminate_id", referencedColumnName = "id")
    private Laminate laminate;

    public Product(double width,
                   double height,
                   Category category,
                   Facet facet,
                   Cutout cutout,
                   Engraving engraving,
                   EdgeTreatment edgeTreatment,
                   Hole hole, Drawing drawing,
                   Laminate laminate
    ) {
        this.width = width;
        this.height = height;
        this.category = category;
        this.facet = facet;
        this.cutout = cutout;
        this.engraving = engraving;
        this.edgeTreatment = edgeTreatment;
        this.hole = hole;
        this.drawing = drawing;
        this.laminate = laminate;
    }
}
