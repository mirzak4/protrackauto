package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.VehicleDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Long> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Long vehicleId = vehicleService.createVehicle(vehicleDTO);
        return ResponseEntity.created(URI.create("/api/vehicle/" + vehicleId))
                .body(vehicleId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
        vehicleDTO.setId(id);
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
