//package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;
//
//import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.DriverDTO;
//import ba.unsa.etf.nbp.VehicleTrackPlatform.service.DriverService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/driver")
//public class DriverController {
//
//    private final DriverService driverService;
//
//    @Autowired
//    public DriverController(DriverService driverService) {
//        this.driverService = driverService;
//    }
//
//    @Operation(summary = "Get all drivers", description = "Returns a list of all drivers")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "List of drivers")
//    })
//    @GetMapping
//    public List<DriverDTO> getAllDrivers() {
//        return driverService.getAllDrivers();
//    }
//
//    @Operation(summary = "Get driver by id", description = "Returns the driver if it exists")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Driver found"),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Driver not found", content = @Content)
//    })
//    @GetMapping("/{id}")
//    public ResponseEntity<DriverDTO> getDriverById(@PathVariable Long id) {
//        return driverService.getDriverById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @Operation(summary = "Create a new driver", description = "Returns the id of the newly created driver")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Driver created"),
//            @ApiResponse(responseCode = "400", description = "Invalid driver payload supplied", content = @Content)
//    })
//    @PostMapping
//    public ResponseEntity<Long> createDriver(@RequestBody DriverDTO driverDTO) {
//        Long driverId = driverService.createDriver(driverDTO);
//        return ResponseEntity.created(URI.create("/api/driver/" + driverId))
//                .body(driverId);
//    }
//
//    @Operation(summary = "Update existing driver", description = "Returns the updated driver")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Driver updated successfully"),
//            @ApiResponse(responseCode = "400", description = "Invalid driver payload supplied", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Driver not found", content = @Content)
//    })
//    @PutMapping("/{id}")
//    public ResponseEntity<DriverDTO> updateDriver(@PathVariable Long id, @RequestBody DriverDTO driverDTO) {
//        driverDTO.setId(id);
//        return ResponseEntity.ok(driverService.updateDriver(driverDTO));
//    }
//
//    @Operation(summary = "Delete driver by id", description = "Deletes the driver with the provided id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "Driver deleted successfully"),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Driver not found", content = @Content)
//    })
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
//        driverService.deleteDriver(id);
//        return ResponseEntity.noContent().build();
//    }
//}
