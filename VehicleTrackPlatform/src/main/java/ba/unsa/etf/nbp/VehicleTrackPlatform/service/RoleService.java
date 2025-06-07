package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Role;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findById(Long id) {
        return roleRepository.findById(id);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAllRoles();
    }
}
