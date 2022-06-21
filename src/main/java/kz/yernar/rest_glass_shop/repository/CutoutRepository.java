package kz.yernar.rest_glass_shop.repository;

import kz.yernar.rest_glass_shop.domain.Cutout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CutoutRepository extends JpaRepository<Cutout, Long> {
}
