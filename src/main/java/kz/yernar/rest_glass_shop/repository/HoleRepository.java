package kz.yernar.rest_glass_shop.repository;

import kz.yernar.rest_glass_shop.domain.Hole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoleRepository extends JpaRepository<Hole, Long> {
}
