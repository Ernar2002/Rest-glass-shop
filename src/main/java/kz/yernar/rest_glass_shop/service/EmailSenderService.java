package kz.yernar.rest_glass_shop.service;


public interface EmailSenderService {
    void sendEmail(String toEmail, String subject, String body) ;
}
