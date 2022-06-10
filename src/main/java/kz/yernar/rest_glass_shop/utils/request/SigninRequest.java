package kz.yernar.rest_glass_shop.utils.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SigninRequest {
    private String email;
    private String password;
}
