package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.RegistrationDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Operation(summary = "Get all registrations", description = "Returns all registrations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of registrations")
    })
    @GetMapping
    public List<RegistrationDTO> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @Operation(summary = "Get registration by id", description = "Returns the registration if it exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registration found"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Registration not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDTO> getRegistrationById(@PathVariable Long id) {
        return registrationService.getRegistrationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new registration", description = "Returns the id of newly created registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registration created"),
            @ApiResponse(responseCode = "400", description = "Invalid registration payload supplied", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Long> createRegistration(@RequestBody RegistrationDTO registrationDTO) {
        Long registrationId = registrationService.createRegistration(registrationDTO);
        return ResponseEntity.created(URI.create("/api/registration/" + registrationId))
                .body(registrationId);
    }

    @Operation(summary = "Update existing registration", description = "Returns the updated registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registration updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid registration payload supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Registration not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDTO> updateRegistration(@PathVariable Long id, @RequestBody RegistrationDTO registrationDTO) {
        registrationDTO.setId(id);
        return ResponseEntity.ok(registrationService.updateRegistration(registrationDTO));
    }

    @Operation(summary = "Delete registration by id", description = "Deletes registration with provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registration deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Registration not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }
}
