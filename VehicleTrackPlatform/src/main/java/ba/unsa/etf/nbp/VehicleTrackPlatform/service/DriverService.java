package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.DriverDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.mappings.DriverMapping;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Driver;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.RoleType;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.DriverRepository;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverService {
    private final DriverRepository driverRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, RoleRepository roleRepository) {
        this.driverRepository = driverRepository;
        this.roleRepository = roleRepository;
    }

    public List<DriverDTO> getAllDrivers() {
        var drivers = driverRepository.findAll();

        return drivers.stream()
                .map(DriverMapping::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DriverDTO> getDriverById(Long id) {
        return driverRepository.findById(id)
                .map(DriverMapping::convertToDTO);
    }

    public Long createDriver(DriverDTO driverDTO) {
        Driver driver = DriverMapping.convertToEntity(driverDTO);

        var role = roleRepository.findRoleByType(RoleType.DRIVER);
        driver.getUser().setRoleId(role.getId());

        return driverRepository.create(driver);
    }

    public DriverDTO updateDriver(DriverDTO driverDTO) {
        Driver driver = DriverMapping.convertToEntity(driverDTO);
        Driver savedDriver = driverRepository.update(driver);
        return DriverMapping.convertToDTO(savedDriver);
    }

    public void deleteDriver(Long userId) {
        driverRepository.deleteByUserId(userId);
    }
}
