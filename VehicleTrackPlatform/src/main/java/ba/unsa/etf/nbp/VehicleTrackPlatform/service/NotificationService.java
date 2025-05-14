package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final EmailService emailService;

    @Autowired
    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendResetPasswordLink(String email, String userFirstName, String verificationCode) throws MessagingException {
        // send email
        String htmlContent = "<p>Hello " + userFirstName + ",</p>" +
                "<p>Please click the link below to reset your password and activate your account: </p>" +
                "<p><a href=\"https://yourapp.com/verify?token=abc123\">Reset password</a></p>"
                + "<p>Your verification code: <b>" + verificationCode + "</b></p>";

        this.emailService.sendEmail(email, "Reset your AutoTrack Pro password", htmlContent);
    }

    public void sendAccountCreatedNotification(String email, String userFirstName, String verificationCode) throws MessagingException {
        // send email
        String htmlContent = "<p>Welcome to our AutoTrack Pro platform " + userFirstName + "!</p>" +
                "<p>Please click the link below to reset your password and activate your account: </p>" +
                "<p><a href=\"https://yourapp.com/verify?token=abc123\">Reset password</a></p>"
                + "<p>Your verification code: <b>" + verificationCode + "</b></p>";

        this.emailService.sendEmail(email, "Welcome to AutoTrack Pro", htmlContent);
    }
}
