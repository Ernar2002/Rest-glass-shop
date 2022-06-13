package kz.yernar.rest_glass_shop.controller.auth;

import kz.yernar.rest_glass_shop.domain.User;
import kz.yernar.rest_glass_shop.service.EmailSenderService;
import kz.yernar.rest_glass_shop.service.UserService;
import kz.yernar.rest_glass_shop.utils.request.ResetPasswordRequest;
import kz.yernar.rest_glass_shop.utils.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping(value = "/api/auth/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class ForgotPasswordRestController {

    private UserService userService;
    private EmailSenderService senderService;

    @Autowired
    public ForgotPasswordRestController(UserService userService, EmailSenderService senderService) {
        this.userService = userService;
        this.senderService = senderService;
    }

    @GetMapping("/reset-password-request")
    public ResponseEntity<?> sendCode(@RequestParam String email){
        try {
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            Random rnd = new Random();
            int code = rnd.nextInt(999999);
            userService.updateResetPasswordToken((String.format("%06d", code)), email);
            senderService.sendEmail(email, "Сброс пароля","Приветствуем, " + email + "!. Для сброса пароля, пожалуйста, введите код: " + (String.format("%06d", code) + ". С наилучшими пожеланиями, команда сайта."));

            return ResponseEntity.ok(new MessageResponse(String.format("%06d", code)));
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
    }

    @GetMapping("/change-password")
    public ResponseEntity<?> showChangePasswordPage(@RequestBody ResetPasswordRequest request) {
        User user = userService.getByResetPasswordToken(request.getToken());
        if(user != null) {
            userService.updatePassword(user, request.getPassword());
            return ResponseEntity.ok(new MessageResponse("Password updated"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
    }
}
