package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.EmployeeDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employees", description = "Returns a list of all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of employees")
    })
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Operation(summary = "Get employee by id", description = "Returns the employee if it exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new employee", description = "Returns the id of the newly created employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created"),
            @ApiResponse(responseCode = "400", description = "Invalid employee payload supplied", content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<Long> createEmployee(@RequestBody EmployeeDTO employeeDTO) throws MessagingException {
        Long employeeId = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.created(URI.create("/api/employee/" + employeeId))
                .body(employeeId);
    }

    @Operation(summary = "Update existing employee", description = "Returns the updated employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid employee payload supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDTO));
    }

    @Operation(summary = "Delete employee by id", description = "Deletes the employee with the provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long userId) {
        employeeService.deleteEmployee(userId);
        return ResponseEntity.noContent().build();
    }
}
