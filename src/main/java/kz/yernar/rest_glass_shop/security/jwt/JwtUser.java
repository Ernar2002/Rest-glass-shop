package kz.yernar.rest_glass_shop.security.jwt;

import kz.yernar.rest_glass_shop.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class JwtUser {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String iin;
    private String address;
    private Set<Role> roles;
}