package kz.yernar.rest_glass_shop.repository;

import kz.yernar.rest_glass_shop.domain.EdgeTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgeTreatmentRepository extends JpaRepository<EdgeTreatment, Long> {
}
