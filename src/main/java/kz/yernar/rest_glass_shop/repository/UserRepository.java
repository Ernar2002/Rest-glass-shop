package kz.yernar.rest_glass_shop.repository;

import kz.yernar.rest_glass_shop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByResetPasswordToken(String token);

}
