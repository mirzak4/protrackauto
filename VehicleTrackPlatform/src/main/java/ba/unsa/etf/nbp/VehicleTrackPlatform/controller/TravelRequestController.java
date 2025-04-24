package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;


import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.TravelRequestDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.TravelRequestService;
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
@RequestMapping("/api/travel-request")
public class TravelRequestController {

    private final TravelRequestService travelRequestService;

    @Autowired
    public TravelRequestController(TravelRequestService travelRequestService) {
        this.travelRequestService = travelRequestService;
    }

    @Operation(summary = "Get all travel requests", description = "Returns all travel requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of travel requests"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
    })
    @GetMapping
    public List<TravelRequestDTO> getAllTravelRequests() {
        return travelRequestService.getAllTravelRequests();
    }

    @Operation(summary = "Get travel request by id", description = "Returns the travel request if it exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Travel request found"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Travel request not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TravelRequestDTO> getTravelRequestById(@PathVariable Long id) {
        return travelRequestService.getTravelRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new travel request", description = "Returns the id of newly created travel request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Travel request created"),
            @ApiResponse(responseCode = "400", description = "Invalid travel request payload supplied", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Long> createTravelRequest(@RequestBody TravelRequestDTO travelRequestDTO) {
        Long travelRequestId = travelRequestService.createTravelRequest(travelRequestDTO);
        return ResponseEntity.created(URI.create("/api/travel-request/" + travelRequestId))
                .body(travelRequestId);
    }

    @Operation(summary = "Update existing travel request", description = "Returns the updated travel request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Travel request updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid travel request payload supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Travel request not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<TravelRequestDTO> updateTravelRequest(@PathVariable Long id, @RequestBody TravelRequestDTO travelRequestDTO) {
        travelRequestDTO.setId(id);
        return ResponseEntity.ok(travelRequestService.updateTravelRequest(travelRequestDTO));
    }

    @Operation(summary = "Delete travel request by id", description = "Deletes travel request with provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Travel request deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Travel request not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravelRequest(@PathVariable Long id) {
        travelRequestService.deleteTravelRequest(id);
        return ResponseEntity.noContent().build();
    }
}
