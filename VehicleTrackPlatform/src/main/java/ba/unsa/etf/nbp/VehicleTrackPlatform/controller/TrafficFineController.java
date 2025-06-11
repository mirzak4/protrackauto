package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.TrafficFineDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.TrafficFineService;
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
@RequestMapping("/api/traffic-fine")
public class TrafficFineController {

    private final TrafficFineService trafficFineService;

    @Autowired
    public TrafficFineController(TrafficFineService trafficFineService) {
        this.trafficFineService = trafficFineService;
    }

    @Operation(summary = "Get all traffic fines", description = "Returns all traffic fines")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of traffic fines")
    })
    @GetMapping
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public List<TrafficFineDTO> getAllTrafficFines() {
        return trafficFineService.getAllTrafficFines();
    }

    @Operation(summary = "Get traffic fine by id", description = "Returns the traffic fine if it exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Traffic fine found"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Traffic fine not found", content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<TrafficFineDTO> getTrafficFineById(@PathVariable Long id) {
        return trafficFineService.getTrafficFineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new traffic fine", description = "Returns the id of newly created traffic fine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Traffic fine created"),
            @ApiResponse(responseCode = "400", description = "Invalid traffic fine payload supplied", content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<Long> createTrafficFine(@RequestBody TrafficFineDTO trafficFineDTO) {
        Long fineId = trafficFineService.createTrafficFine(trafficFineDTO);
        return ResponseEntity.created(URI.create("/api/traffic-fine/" + fineId))
                .body(fineId);
    }

    @Operation(summary = "Update existing traffic fine", description = "Returns the updated traffic fine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Traffic fine updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid traffic fine payload supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Traffic fine not found", content = @Content)
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<TrafficFineDTO> updateTrafficFine(@PathVariable Long id, @RequestBody TrafficFineDTO trafficFineDTO) {
        trafficFineDTO.setId(id);
        return ResponseEntity.ok(trafficFineService.updateTrafficFine(trafficFineDTO));
    }

    @Operation(summary = "Get all traffic fines for a vehicle by vehicle ID", description = "Returns a list of traffic fines for the specified vehicle ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Traffic fines found"),
            @ApiResponse(responseCode = "400", description = "Invalid vehicle ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "No traffic fines found for vehicle ID", content = @Content)
    })
    @GetMapping("/vehicle/{vehicleId}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<List<TrafficFineDTO>> getFinesByVehicleId(@PathVariable Long vehicleId) {
        List<TrafficFineDTO> fines = trafficFineService.getAllTrafficFinesByVehicleId(vehicleId);
        return ResponseEntity.ok(fines);
    }

    @Operation(summary = "Get all traffic fines for a driver by driver ID", description = "Returns a list of traffic fines for the specified driver ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Traffic fines found"),
            @ApiResponse(responseCode = "400", description = "Invalid driver ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "No traffic fines found for driver ID", content = @Content)
    })
    @GetMapping("/driver/{driverId}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<List<TrafficFineDTO>> getFinesByDriverId(@PathVariable Long driverId) {
        List<TrafficFineDTO> fines = trafficFineService.getAllTrafficFinesByDriverId(driverId);
        return ResponseEntity.ok(fines);
    }

    @Operation(summary = "Delete traffic fine by id", description = "Deletes traffic fine with provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Traffic fine deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Traffic fine not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<Void> deleteTrafficFine(@PathVariable Long id) {
        trafficFineService.deleteTrafficFine(id);
        return ResponseEntity.noContent().build();
    }
}
