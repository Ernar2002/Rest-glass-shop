package kz.yernar.rest_glass_shop.controller.auth;

import kz.yernar.rest_glass_shop.domain.Role;
import kz.yernar.rest_glass_shop.domain.User;
import kz.yernar.rest_glass_shop.security.JwtUserDetailsService;
import kz.yernar.rest_glass_shop.security.jwt.JwtUtil;
import kz.yernar.rest_glass_shop.service.UserService;
import kz.yernar.rest_glass_shop.utils.request.SigninRequest;
import kz.yernar.rest_glass_shop.utils.request.SignupRequest;
import kz.yernar.rest_glass_shop.utils.response.DefaultResponse;
import kz.yernar.rest_glass_shop.utils.response.JwtResponse;
import kz.yernar.rest_glass_shop.utils.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/auth/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest requestDto){

        Optional<User> findUser = Optional.ofNullable(userService.findByEmail(requestDto.getEmail()));

        if(findUser.isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: user exists"));
        }

        if(requestDto.getEmail() == null || requestDto.getPassword() == null || requestDto.getFirstName() == null || requestDto.getLastName() == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Fill all data"));
        }

        User newUser = new User();
        newUser.setEmail(requestDto.getEmail());
        newUser.setPassword(requestDto.getPassword());
        newUser.setFirstName(requestDto.getFirstName());
        newUser.setLastName(requestDto.getLastName());

        userService.save(newUser);
        return ResponseEntity.ok(new MessageResponse("User created"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody SigninRequest request) {
        try {
            String email = request.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            String token = jwtUtil.createToken(user.getEmail());

            List<String> roles = new ArrayList<>();

            for (Role role : user.getRoles()
            ) {
                roles.add(role.getName());
            }

            return ResponseEntity.ok(new JwtResponse(token,
                    user.getId(),
                    user.getEmail(),
                    roles));
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().body(new MessageResponse("Email or password is incorrect."));
        }
    }

    @GetMapping("/login")
    public DefaultResponse<User> login(@RequestParam String email, @RequestParam String password) {
        User findUser = userService.findByEmail(email);

        DefaultResponse<User> loginResponse = new DefaultResponse<User>();
        if(findUser!=null){
            if(passwordEncoder.matches(password, findUser.getPassword())){
                loginResponse.setT(findUser);
                loginResponse.setError(false);
                return loginResponse;
            } else {
                loginResponse.setMessage("Invalid password");
                loginResponse.setError(true);
                return loginResponse;
            }

        } else {
            loginResponse.setMessage("User not found");
            loginResponse.setError(true);
            return loginResponse;
        }
    }

}
