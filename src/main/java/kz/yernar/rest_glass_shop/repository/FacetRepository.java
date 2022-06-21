package kz.yernar.rest_glass_shop.repository;

import kz.yernar.rest_glass_shop.domain.Facet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacetRepository extends JpaRepository<Facet, Long> {
}
