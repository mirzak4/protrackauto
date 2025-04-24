package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.ServiceRequestDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.ServiceRequestService;
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
@RequestMapping("/api/service-request")
public class ServiceRequestController {
    private final ServiceRequestService serviceRequestService;

    @Autowired
    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @Operation(summary = "Get all service requests", description = "Returns all service requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of service requests")
    })
    @GetMapping
    public List<ServiceRequestDTO> getAllServiceRequests() {
        return serviceRequestService.getAllServiceRequests();
    }

    @Operation(summary = "Get service request by id", description = "Returns the service request if it exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service request found"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Service request not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequestDTO> getServiceRequestById(@PathVariable Long id) {
        return serviceRequestService.getServiceRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new service request", description = "Returns the id of newly created service request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service request created"),
            @ApiResponse(responseCode = "400", description = "Invalid service request payload supplied", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Long> createServiceRequest(@RequestBody ServiceRequestDTO serviceRequestDTO) {
        Long id = serviceRequestService.createServiceRequest(serviceRequestDTO);
        return ResponseEntity.created(URI.create("/api/service-request/" + id))
                .body(id);
    }

    @Operation(summary = "Update existing service request", description = "Returns the updated service request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service request updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid service request payload supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Service request not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ServiceRequestDTO> updateServiceRequest(@PathVariable Long id, @RequestBody ServiceRequestDTO serviceRequestDTO) {
        serviceRequestDTO.setId(id);
        return ResponseEntity.ok(serviceRequestService.updateServiceRequest(serviceRequestDTO));
    }

    @Operation(summary = "Delete service request by id", description = "Deletes service request with provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Service request deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Service request not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRequest(@PathVariable Long id) {
        serviceRequestService.deleteServiceRequest(id);
        return ResponseEntity.noContent().build();
    }
}
