package kz.yernar.rest_glass_shop.repository;

import kz.yernar.rest_glass_shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
