package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class NotificationService {
    private final EmailService emailService;
    private static final String FRONTEND_URL = "http://localhost:4200"; // You can make this configurable through application.properties

    @Autowired
    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendResetPasswordLink(String email, String userFirstName, String verificationCode) throws MessagingException {
        String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8);
        String resetLink = FRONTEND_URL + "/reset-password?email=" + encodedEmail;

        String htmlContent = "<p>Hello " + userFirstName + ",</p>" +
                "<p>Please click the link below to reset your password: </p>" +
                "<p><a href=\"" + resetLink + "\">Reset password</a></p>" +
                "<p>Your verification code: <b>" + verificationCode + "</b></p>" +
                "<p>If you did not request this password reset, please ignore this email.</p>";

        this.emailService.sendEmail(email, "Reset your AutoTrack Pro password", htmlContent);
    }

    public void sendAccountCreatedNotification(String email, String userFirstName, String verificationCode) throws MessagingException {
        String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8);
        String resetLink = FRONTEND_URL + "/reset-password?email=" + encodedEmail;

        String htmlContent = "<p>Welcome to our AutoTrack Pro platform " + userFirstName + "!</p>" +
                "<p>Please click the link below to set your password and activate your account: </p>" +
                "<p><a href=\"" + resetLink + "\">Set password</a></p>" +
                "<p>Your verification code: <b>" + verificationCode + "</b></p>" +
                "<p>If you did not create this account, please ignore this email.</p>";

        this.emailService.sendEmail(email, "Welcome to AutoTrack Pro", htmlContent);
    }
}
