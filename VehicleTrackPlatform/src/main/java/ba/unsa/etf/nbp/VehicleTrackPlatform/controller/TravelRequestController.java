package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;


import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.TravelRequestDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.TravelRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/travel-requests")
public class TravelRequestController {
    private final TravelRequestService travelRequestService;

    public TravelRequestController(TravelRequestService travelRequestService) {
        this.travelRequestService = travelRequestService;
    }

    @GetMapping
    public List<TravelRequestDTO> getAllTravelRequests() {
        return travelRequestService.getAllTravelRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelRequestDTO> getTravelRequestById(@PathVariable Long id) {
        return travelRequestService.getTravelRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TravelRequestDTO> createTravelRequest(@RequestBody TravelRequestDTO travelRequestDTO) {
        TravelRequestDTO savedRequest = travelRequestService.saveTravelRequest(travelRequestDTO);
        return ResponseEntity.created(URI.create("/api/travel-requests/" + savedRequest.getId()))
                .body(savedRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelRequestDTO> updateTravelRequest(@PathVariable Long id, @RequestBody TravelRequestDTO travelRequestDTO) {
        travelRequestDTO.setId(id);
        return ResponseEntity.ok(travelRequestService.saveTravelRequest(travelRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravelRequest(@PathVariable Long id) {
        travelRequestService.deleteTravelRequest(id);
        return ResponseEntity.noContent().build();
    }
}
