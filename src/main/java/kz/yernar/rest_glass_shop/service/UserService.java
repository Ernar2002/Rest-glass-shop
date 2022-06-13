package kz.yernar.rest_glass_shop.service;


import kz.yernar.rest_glass_shop.domain.User;

public interface UserService extends BaseService<User>{

    User findByEmail(String email);
    void updateResetPasswordToken(String token, String email);
    User getByResetPasswordToken(String token);
    void updatePassword(User user, String newPassword);

}
