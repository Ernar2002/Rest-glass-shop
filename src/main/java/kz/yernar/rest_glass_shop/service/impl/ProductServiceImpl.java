package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.Product;
import kz.yernar.rest_glass_shop.exception.NotFoundException;
import kz.yernar.rest_glass_shop.repository.*;
import kz.yernar.rest_glass_shop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final FacetRepository facetRepository;
    private final CutoutRepository cutoutRepository;
    private final EdgeTreatmentRepository edgeTreatmentRepository;
    private final HoleRepository holeRepository;

    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            FacetRepository facetRepository,
            CutoutRepository cutoutRepository,
            EdgeTreatmentRepository edgeTreatmentRepository,
            HoleRepository holeRepository
    ){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.facetRepository = facetRepository;
        this.cutoutRepository = cutoutRepository;
        this.edgeTreatmentRepository = edgeTreatmentRepository;
        this.holeRepository = holeRepository;
    }

    @Override
    public void save(Product product) {
        if(product.getFacet() != null){
            facetRepository.save(product.getFacet());
            product.getFacet().setProduct(product);
        }

        if(product.getCutout() != null){
            cutoutRepository.save(product.getCutout());
            product.getCutout().setProduct(product);
        }

        if(product.getEdgeTreatment() != null){
            edgeTreatmentRepository.save(product.getEdgeTreatment());
            product.getEdgeTreatment().setProduct(product);
        }

        if(product.getHole() != null){
            holeRepository.save(product.getHole());
            product.getHole().setProduct(product);
        }

        productRepository.save(product);
    }

    @Override
    public void update(Long id, Product product) {
        Product productFromDb = getById(id);

        productFromDb.setCategory(product.getCategory());
        productFromDb.setCutout(product.getCutout());
        productFromDb.setDrawing(product.getDrawing());
        productFromDb.setEngraving(product.getEngraving());
        productFromDb.setFacet(product.getFacet());
        productFromDb.setLaminate(product.getLaminate());
        productFromDb.setEdgeTreatment(product.getEdgeTreatment());
        productFromDb.setHole(product.getHole());
        productFromDb.setHeight(product.getHeight());
        productFromDb.setWidth(product.getWidth());

        save(productFromDb);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(getById(id));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
