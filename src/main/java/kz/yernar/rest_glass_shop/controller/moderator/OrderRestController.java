package kz.yernar.rest_glass_shop.controller.moderator;

import kz.yernar.rest_glass_shop.domain.Order;
import kz.yernar.rest_glass_shop.domain.User;
import kz.yernar.rest_glass_shop.domain.enums.EOrderStatus;
import kz.yernar.rest_glass_shop.service.*;
import kz.yernar.rest_glass_shop.utils.request.OrderProductRequest;
import kz.yernar.rest_glass_shop.utils.response.DefaultResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/mod/user", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderRestController {

    private final OrderService orderService;
    private final CategoryService categoryService;
    private final CutoutService cutoutService;
    private final EdgeTreatmentService edgeTreatmentService;
    private final FacetService facetService;
    private final HoleService holeService;
    private final UserService userService;

    public OrderRestController(
            OrderService orderService,
            CategoryService categoryService,
            CutoutService cutoutService,
            EdgeTreatmentService edgeTreatmentService,
            FacetService facetService,
            HoleService holeService,
            UserService userService
    ) {
        this.orderService = orderService;
        this.categoryService = categoryService;
        this.cutoutService = cutoutService;
        this.edgeTreatmentService = edgeTreatmentService;
        this.facetService = facetService;
        this.holeService = holeService;
        this.userService = userService;
    }

    @GetMapping("{user-id}")
    public ResponseEntity<User> getProducts(@PathVariable("user-id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("{user-id}/order/{id}")
    public ResponseEntity<DefaultResponse<Order>> getProductById(@PathVariable("user-id") Long userId, @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(new DefaultResponse<>(false, "", orderService.getById(id)));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new DefaultResponse<>(true, "Not found: " + e, null));
        }
    }

    @PostMapping("{user-id}/order/add")
    public ResponseEntity<DefaultResponse<Order>> addProduct(@PathVariable("user-id") Long userId, @RequestBody OrderProductRequest request){
         try {
             if(categoryService.getById(request.getCategory().getId()) == null){
                 return ResponseEntity.badRequest().body(new DefaultResponse<>(true, "Category not found", null));
             }

             if(userService.getById(userId) == null){
                 return ResponseEntity.badRequest().body(new DefaultResponse<>(true, "User not found", null));
             }

             Order order = new Order(request.getCity(),
                     request.getAddress(),
                     request.getPaymentMethod(),
                     request.getDeliveryType(),
                     request.getQuantity(),
                     request.getPrice(),
                     request.getComment(),
                     request.isHardening(),
                     request.getWidth(),
                     request.getHeight(),
                     userService.getById(userId),
                     request.getCategory(),
                     request.getFacet(),
                     request.getCutout(),
                     request.getEngraving(),
                     request.getEdgeTreatment(),
                     request.getHole(),
                     request.getDrawing(),
                     request.getLaminate()
             );

             order.setOrderDate(new Date());
             order.setOrderStatus(EOrderStatus.NEW);

             orderService.save(order);

             return ResponseEntity.ok(new DefaultResponse<>(false, "Added successfully", order));
         } catch (Exception e){
             return ResponseEntity.badRequest().body(new DefaultResponse<>(true, "Error: " + e, null));
         }
    }

    @PutMapping("{user-id}/{id}/update")
    public ResponseEntity<DefaultResponse<Order>> updateProduct(@PathVariable("user-id") Long userId, @PathVariable("id") Long id,
                                                                @RequestBody OrderProductRequest request) {
        try {
            Order order = new Order(request.getCity(),
                    request.getAddress(),
                    request.getPaymentMethod(),
                    request.getDeliveryType(),
                    request.getQuantity(),
                    request.getPrice(),
                    request.getComment(),
                    request.isHardening(),
                    request.getWidth(),
                    request.getHeight(),
                    userService.getById(userId),
                    request.getCategory(),
                    request.getFacet(),
                    request.getCutout(),
                    request.getEngraving(),
                    request.getEdgeTreatment(),
                    request.getHole(),
                    request.getDrawing(),
                    request.getLaminate()
            );

            orderService.update(id, order);

            return ResponseEntity.ok(new DefaultResponse<>(false, "Updated successfully", orderService.getById(id)));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new DefaultResponse<>(true, "Error: " + e, null));
        }
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<DefaultResponse<Order>> deleteProduct(@PathVariable("id") Long id){
        try {
            orderService.delete(id);

            return ResponseEntity.ok(new DefaultResponse<>(false, "Deleted successfully", null));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new DefaultResponse<>(true, "Error: " + e, null));
        }
    }
}
