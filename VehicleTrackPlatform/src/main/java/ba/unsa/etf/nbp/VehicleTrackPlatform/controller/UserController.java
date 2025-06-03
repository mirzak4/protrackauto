package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.common.authentication.JwtHelper;
import ba.unsa.etf.nbp.VehicleTrackPlatform.features.resetpassword.ResetPasswordRequest;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.User;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.UserDetailService;
import ba.unsa.etf.nbp.VehicleTrackPlatform.features.login.LoginRequest;
import ba.unsa.etf.nbp.VehicleTrackPlatform.features.login.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserDetailService userDetailService;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    @Autowired
    public UserController(UserDetailService userDetailService, AuthenticationManager authenticationManager, JwtHelper jwtHelper) {
        this.userDetailService = userDetailService;
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
    }

    @Operation(summary = "Logins user into the system", description = "Return user details with generated access token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content)
    })
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = (User)authentication.getPrincipal();

        String token = this.jwtHelper.generateToken(user.getUsername(), user.getAuthorities());

        return ResponseEntity.ok(new LoginResponse(request.getEmail(), user.getFirstName(), user.getLastName(), token));
    }

    @Operation(summary = "Sends password reset link to the provided user email address", description = "Sends password reset link and returns no content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset link sent successfully"),
            @ApiResponse(responseCode = "404", description = "User with provided email address not found", content = @Content)
    })
    @PostMapping("password-reset/link")
    public ResponseEntity sendResetPasswordLink(@RequestParam String email) throws MessagingException {
        userDetailService.sendResetPasswordLink(email);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Resets password for the user with provided email address", description = "Resets password for the user and returns no content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid verification code", content = @Content),
            @ApiResponse(responseCode = "404", description = "User with provided email address not found", content = @Content)
    })
    @PostMapping("password-reset")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordRequest request) {
        userDetailService.resetPassword(request);
        return ResponseEntity.ok().build();
    }
}
