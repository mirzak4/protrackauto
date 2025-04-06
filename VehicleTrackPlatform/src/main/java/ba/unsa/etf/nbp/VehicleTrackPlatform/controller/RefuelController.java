package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.RefuelDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.RefuelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/refuel")
public class RefuelController {
    private final RefuelService refuelService;

    public RefuelController(RefuelService refuelService) {
        this.refuelService = refuelService;
    }

    @GetMapping
    public List<RefuelDTO> getAllRefuels() {
        return refuelService.getAllRefuels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefuelDTO> getRefuelById(@PathVariable Long id) {
        return refuelService.getRefuelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RefuelDTO> createRefuel(@RequestBody RefuelDTO refuelDTO) {
        RefuelDTO savedRefuel = refuelService.saveRefuel(refuelDTO);
        return ResponseEntity.created(URI.create("/api/refuels/" + savedRefuel.getId()))
                .body(savedRefuel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefuelDTO> updateRefuel(@PathVariable Long id, @RequestBody RefuelDTO refuelDTO) {
        refuelDTO.setId(id);
        return ResponseEntity.ok(refuelService.saveRefuel(refuelDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefuel(@PathVariable Long id) {
        refuelService.deleteRefuel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<RefuelDTO> getRefuelsByVehicle(@PathVariable Long vehicleId) {
        return refuelService.getRefuelsByVehicle(vehicleId);
    }

    @GetMapping("/station/{stationId}")
    public List<RefuelDTO> getRefuelsByStation(@PathVariable Long stationId) {
        return refuelService.getRefuelsByStation(stationId);
    }

}
