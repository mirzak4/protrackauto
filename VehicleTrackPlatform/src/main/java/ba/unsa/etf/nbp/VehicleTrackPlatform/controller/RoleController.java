package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Role;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Get all roles", description = "Returns a list of all system roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content)
    })
    @GetMapping
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @Operation(summary = "Get role name by ID", description = "Returns the name of the role by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role name retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<String> getRoleNameById(@PathVariable Long id) {
        try {
            Role role = roleService.findById(id);
            if (role == null) return ResponseEntity.notFound().build();

            String roleName = role.getName().contains(".")
                    ? role.getName().substring(role.getName().indexOf('.') + 1)
                    : role.getName();

            return ResponseEntity.ok(roleName);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
