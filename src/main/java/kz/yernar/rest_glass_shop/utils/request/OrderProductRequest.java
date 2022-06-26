package kz.yernar.rest_glass_shop.utils.request;

import kz.yernar.rest_glass_shop.domain.*;
import kz.yernar.rest_glass_shop.domain.enums.EDeliveryType;
import kz.yernar.rest_glass_shop.domain.enums.EPaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductRequest {
    private String city;
    private String address;
    private EPaymentMethod paymentMethod;
    private EDeliveryType deliveryType;
    private int quantity;
    private String comment;
    private boolean hardening;
    private double width;
    private double height;
    private double price;
    private Category category;
    private Facet facet;
    private Cutout cutout;
    private Engraving engraving;
    private EdgeTreatment edgeTreatment;
    private Hole hole;
    private Drawing drawing;
    private Laminate laminate;
}
