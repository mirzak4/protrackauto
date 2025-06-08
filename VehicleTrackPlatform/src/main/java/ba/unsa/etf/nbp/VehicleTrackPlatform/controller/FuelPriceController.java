package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.FuelPriceDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.FuelPriceService;
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
@RequestMapping("/api/fuel-price")
public class FuelPriceController {

    private final FuelPriceService fuelPriceService;

    @Autowired
    public FuelPriceController(FuelPriceService fuelPriceService) {
        this.fuelPriceService = fuelPriceService;
    }

    @Operation(summary = "Get all fuel prices", description = "Returns all fuel prices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of fuel prices")
    })
    @GetMapping
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public List<FuelPriceDTO> getAllFuelPrices() {
        return fuelPriceService.getAllFuelPrices();
    }

    @Operation(summary = "Get all fuel prices for a company", description = "Returns all fuel prices for the specified company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of company's fuel prices"),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
    })
    @GetMapping("/company/{companyId}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public List<FuelPriceDTO> getCompanyFuelPrices(@PathVariable Long companyId) {
        return fuelPriceService.getCompanyFuelPrices(companyId);
    }

    @Operation(summary = "Get fuel price by id", description = "Returns the fuel price if it exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fuel price found"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Fuel price not found", content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<FuelPriceDTO> getFuelPriceById(@PathVariable Long id) {
        return fuelPriceService.getFuelPriceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new fuel price", description = "Returns the id of newly created fuel price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fuel price created"),
            @ApiResponse(responseCode = "400", description = "Invalid fuel price payload supplied", content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<Long> createFuelPrice(@RequestBody FuelPriceDTO fuelPriceDTO) {
        Long fuelPriceId = fuelPriceService.createFuelPrice(fuelPriceDTO);
        return ResponseEntity.created(URI.create("/api/fuel-price/" + fuelPriceId))
                .body(fuelPriceId);
    }

    @Operation(summary = "Update existing fuel price", description = "Returns the updated fuel price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fuel price updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid fuel price payload supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Fuel price not found", content = @Content)
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<FuelPriceDTO> updateFuelPrice(@PathVariable Long id, @RequestBody FuelPriceDTO fuelPriceDTO) {
        fuelPriceDTO.setId(id);
        return ResponseEntity.ok(fuelPriceService.updateFuelPrice(fuelPriceDTO));
    }

    @Operation(summary = "Delete fuel price by id", description = "Deletes fuel price with provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Fuel price deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Fuel price not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<Void> deleteFuelPrice(@PathVariable Long id) {
        fuelPriceService.deleteFuelPrice(id);
        return ResponseEntity.noContent().build();
    }
}
