package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.common.authentication.VerificationCodeGenerator;
import ba.unsa.etf.nbp.VehicleTrackPlatform.features.resetpassword.ResetPasswordRequest;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.User;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final NotificationService notificationService;
    private final UserRepository userRepository;

    @Autowired
    public UserDetailService(NotificationService notificationService, UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException  {
        var user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with provided email " + email + " does not exist");
        }

        if (!user.get().isEnabled()) {
            throw new UsernameNotFoundException("User with provided email " + email + " does not have activated account.");
        }

        return user.get();
    }

    public void sendResetPasswordLink(String email) throws MessagingException {
        var userDetails = userRepository.findByEmail(email);

        if (userDetails.isEmpty()) {
            throw new UsernameNotFoundException("User with provided email" + email + "does not exist");
        }

        var user = (User)userDetails.get();
        String verificationCode = VerificationCodeGenerator.generateCode();

        this.userRepository.updateVerificationCode(user.getId(), verificationCode);
        this.notificationService.sendResetPasswordLink(email, user.getFirstName(), verificationCode);
    }

    public void resetPassword(ResetPasswordRequest request) {
        var user = this.userRepository.findByEmail(request.getEmail());
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with provided email does not exist.");
        }

        var userId = ((User)user.get()).getId();
        var isVerificationCodeValid = this.userRepository.isVerificationCodeValid(userId, request.getVerificationCode());
        if (!isVerificationCodeValid) {
            throw new IllegalArgumentException("Invalid verification code");
        }

        this.userRepository.changePasswordAndActivateUser(userId, request.getNewPassword());
    }
}
