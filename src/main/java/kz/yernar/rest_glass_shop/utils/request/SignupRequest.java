package kz.yernar.rest_glass_shop.utils.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
