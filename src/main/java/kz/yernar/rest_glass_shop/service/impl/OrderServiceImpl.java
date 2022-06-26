package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.Order;
import kz.yernar.rest_glass_shop.domain.enums.EOrderStatus;
import kz.yernar.rest_glass_shop.repository.*;
import kz.yernar.rest_glass_shop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DrawingRepository drawingRepository;
    private final EngravingRepository engravingRepository;
    private final LaminateRepository laminateRepository;
    private final CategoryRepository categoryRepository;
    private final FacetRepository facetRepository;
    private final CutoutRepository cutoutRepository;
    private final EdgeTreatmentRepository edgeTreatmentRepository;
    private final HoleRepository holeRepository;

    public OrderServiceImpl(
            UserRepository userRepository,
            OrderRepository orderRepository,
            CategoryRepository categoryRepository,
            FacetRepository facetRepository,
            CutoutRepository cutoutRepository,
            EdgeTreatmentRepository edgeTreatmentRepository,
            HoleRepository holeRepository,
            DrawingRepository drawingRepository,
            EngravingRepository engravingRepository,
            LaminateRepository laminateRepository
    ){
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.facetRepository = facetRepository;
        this.cutoutRepository = cutoutRepository;
        this.edgeTreatmentRepository = edgeTreatmentRepository;
        this.holeRepository = holeRepository;
        this.drawingRepository = drawingRepository;
        this.engravingRepository = engravingRepository;
        this.laminateRepository = laminateRepository;
    }

    @Override
    public void save(Order order) {

        if(order.getCutout() != null){
            cutoutRepository.save(order.getCutout());
//            order.getCutout().setOrder(order);
        }

        if(order.getDrawing() != null){
            drawingRepository.save(order.getDrawing());
//            order.getDrawing().setOrder(order);
        }

        if(order.getEdgeTreatment() != null){
            edgeTreatmentRepository.save(order.getEdgeTreatment());
//            order.getEdgeTreatment().setOrder(order);
        }

        if(order.getEngraving() != null){
            engravingRepository.save(order.getEngraving());
//            order.getEngraving().setOrder(order);
        }

        if(order.getFacet() != null){
            facetRepository.save(order.getFacet());
//            order.getFacet().setOrder(order);
        }

        if(order.getHole() != null){
            holeRepository.save(order.getHole());
//            order.getHole().setOrder(order);
        }

        if(order.getLaminate() != null){
            laminateRepository.save(order.getLaminate());
         }

        order.setTotalPrice(order.getPrice() * order.getQuantity());
//        order.getUser().addOrder(order);
        userRepository.save(order.getUser());
        orderRepository.save(order);
    }

    @Override
    public void update(Long id, Order order) {
        Order orderFromDb = getById(id);

        orderFromDb.setCategory(order.getCategory());
        orderFromDb.setCutout(order.getCutout());
        orderFromDb.setDrawing(order.getDrawing());
        orderFromDb.setEngraving(order.getEngraving());
        orderFromDb.setFacet(order.getFacet());
        orderFromDb.setLaminate(order.getLaminate());
        orderFromDb.setEdgeTreatment(order.getEdgeTreatment());
        orderFromDb.setHole(order.getHole());
        orderFromDb.setHeight(order.getHeight());
        orderFromDb.setWidth(order.getWidth());

        save(orderFromDb);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(getById(id));
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
