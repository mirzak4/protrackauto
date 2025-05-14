package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.common.authentication.Roles;
import ba.unsa.etf.nbp.VehicleTrackPlatform.common.authentication.VerificationCodeGenerator;
import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.DriverDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.mappings.DriverMapping;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Driver;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.DriverRepository;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.RoleRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverService {
    private final NotificationService notificationService;
    private final DriverRepository driverRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DriverService(NotificationService notificationService, DriverRepository driverRepository, RoleRepository roleRepository) {
        this.notificationService = notificationService;
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
        var driver = driverRepository.findById(id)
                .map(DriverMapping::convertToDTO);
        return driver;
    }

    public Long createDriver(DriverDTO driverDTO) throws MessagingException {
        Driver driver = DriverMapping.convertToEntity(driverDTO);

        var role = roleRepository.findRoleByName(Roles.DRIVER);
        driver.getUser().setRoleId(role.getId());
        driver.getUser().setActive(false);

        String verificationCode = VerificationCodeGenerator.generateCode();
        driver.getUser().setLastVerificationCode(verificationCode);

        var createdDriverId = driverRepository.create(driver);
        this.notificationService.sendAccountCreatedNotification(driver.getUser().getEmail(), driver.getUser().getFirstName(), verificationCode);

        return createdDriverId;
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
