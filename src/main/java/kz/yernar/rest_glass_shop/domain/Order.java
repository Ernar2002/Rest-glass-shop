package kz.yernar.rest_glass_shop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kz.yernar.rest_glass_shop.domain.enums.EDeliveryType;
import kz.yernar.rest_glass_shop.domain.enums.EOrderStatus;
import kz.yernar.rest_glass_shop.domain.enums.EPaymentMethod;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double price;

    @Column(name = "total_price",nullable = false)
    private double totalPrice;

    @CreatedDate
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    private String city;
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private EPaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type", nullable = false)
    private EDeliveryType deliveryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EOrderStatus orderStatus;

    @Column(nullable = false)
    private int quantity;

    private String comment;

    private boolean hardening;

    @Column(nullable = false)
    private double width;

    @Column(nullable = false)
    private double height;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonManagedReference(value = "user-order")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference(value = "category-order")
    @EqualsAndHashCode.Exclude
    private Category category;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "facet_id", referencedColumnName = "id")
//    @JsonManagedReference
    private Facet facet;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cutout_id", referencedColumnName = "id")
//    @JsonManagedReference
    private Cutout cutout;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "engraving_id", referencedColumnName = "id")
//    @JsonManagedReference
    private Engraving engraving;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "edge_treatment_id", referencedColumnName = "id")
//    @JsonManagedReference
    private EdgeTreatment edgeTreatment;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hole_id", referencedColumnName = "id")
//    @JsonManagedReference
    private Hole hole;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "drawing_id", referencedColumnName = "id")
//    @JsonManagedReference
    private Drawing drawing;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "laminate_id", referencedColumnName = "id")
//    @JsonManagedReference
    private Laminate laminate;

    public Order(String city, String address, EPaymentMethod paymentMethod, EDeliveryType deliveryType, int quantity, double price, String comment, boolean hardening, double width, double height, User user, Category category, Facet facet, Cutout cutout, Engraving engraving, EdgeTreatment edgeTreatment, Hole hole, Drawing drawing, Laminate laminate) {
        this.city = city;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.deliveryType = deliveryType;
        this.quantity = quantity;
        this.price = price;
        this.comment = comment;
        this.hardening = hardening;
        this.width = width;
        this.height = height;
        this.user = user;
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
