package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.FuelDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.FuelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/fuel")
public class FuelController {

    private final FuelService fuelService;

    @Autowired
    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @Operation(summary = "Get all fuels", description = "Returns all fuels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of fuels")
    })
    @GetMapping
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public List<FuelDTO> getAllFuels() {
        return fuelService.getAllFuels();
    }

    @Operation(summary = "Get fuel by id", description = "Returns the fuel if it exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fuel found"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Fuel not found", content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<FuelDTO> getFuelById(@PathVariable Long id) {
        return fuelService.getFuelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new fuel", description = "Returns the id of newly created fuel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fuel created"),
            @ApiResponse(responseCode = "400", description = "Invalid fuel payload supplied", content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<Long> createFuel(@RequestBody FuelDTO fuelDTO) {
        Long fuelId = fuelService.createFuel(fuelDTO);
        return ResponseEntity.created(URI.create("/api/fuel/" + fuelId))
                .body(fuelId);
    }

    @Operation(summary = "Update existing fuel", description = "Returns the updated fuel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fuel updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid fuel payload supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Fuel not found", content = @Content)
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<FuelDTO> updateFuel(@PathVariable Long id, @RequestBody FuelDTO fuelDTO) {
        fuelDTO.setId(id);
        return ResponseEntity.ok(fuelService.updateFuel(fuelDTO));
    }

    @Operation(summary = "Delete fuel by id", description = "Deletes fuel with provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Fuel deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Fuel not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<Void> deleteFuel(@PathVariable Long id) {
        fuelService.deleteFuel(id);
        return ResponseEntity.noContent().build();
    }
}
