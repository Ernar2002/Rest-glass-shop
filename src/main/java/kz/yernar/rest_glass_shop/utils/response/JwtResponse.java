package kz.yernar.rest_glass_shop.utils.response;

import kz.yernar.rest_glass_shop.domain.Role;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String email;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String email, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.email = email;
    this.roles = roles;
  }

}
