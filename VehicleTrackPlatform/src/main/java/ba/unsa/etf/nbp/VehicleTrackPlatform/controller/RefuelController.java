package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.RefuelDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.RefuelService;
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
@RequestMapping("/api/refuel")
public class RefuelController {

    private final RefuelService refuelService;

    @Autowired
    public RefuelController(RefuelService refuelService) {
        this.refuelService = refuelService;
    }

    @Operation(summary = "Get all refuels", description = "Returns all refuel records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of refuels")
    })
    @GetMapping
    public List<RefuelDTO> getAllRefuels() {
        return refuelService.getAllRefuels();
    }

    @Operation(summary = "Get refuel by id", description = "Returns the refuel if it exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Refuel found"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Refuel not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RefuelDTO> getRefuelById(@PathVariable Long id) {
        return refuelService.getRefuelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new refuel", description = "Returns the id of newly created refuel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Refuel created"),
            @ApiResponse(responseCode = "400", description = "Invalid refuel payload supplied", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Long> createRefuel(@RequestBody RefuelDTO refuelDTO) {
        Long refuelId = refuelService.createRefuel(refuelDTO);
        return ResponseEntity.created(URI.create("/api/refuel/" + refuelId))
                .body(refuelId);
    }

    @Operation(summary = "Update existing refuel", description = "Returns the updated refuel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Refuel updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid refuel payload supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Refuel not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<RefuelDTO> updateRefuel(@PathVariable Long id, @RequestBody RefuelDTO refuelDTO) {
        refuelDTO.setId(id);
        return ResponseEntity.ok(refuelService.updateRefuel(refuelDTO));
    }

    @Operation(summary = "Delete refuel by id", description = "Deletes refuel with provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Refuel deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Refuel not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefuel(@PathVariable Long id) {
        refuelService.deleteRefuel(id);
        return ResponseEntity.noContent().build();
    }
}
