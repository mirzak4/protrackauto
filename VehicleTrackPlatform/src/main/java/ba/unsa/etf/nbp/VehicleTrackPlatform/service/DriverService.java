package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.DriverDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Driver;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    private DriverDTO convertToDTO(Driver driver) {
        var dto = new DriverDTO(
                driver.getId(),
                driver.getFirstName(),
                driver.getLastName(),
                driver.getBirthDate(),
                driver.getLicenseNumber(),
                driver.getLicenseExpiry(),
                driver.getAddress(),
                driver.getPhoneNumber(),
                driver.getEmail(),
                driver.getEmploymentDate()
        );

        dto.setCreatedAt(driver.getCreatedAt());
        dto.setCreatedBy(driver.getCreatedBy());
        dto.setModifiedAt(driver.getModifiedAt());
        dto.setModifiedBy(driver.getModifiedBy());

        return dto;
    }

    private Driver convertToEntity(DriverDTO dto) {
        return new Driver(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getLicenseNumber(),
                dto.getLicenseExpiry(),
                dto.getAddress(),
                dto.getPhoneNumber(),
                dto.getEmail(),
                dto.getEmploymentDate()
        );
    }

    public List<DriverDTO> getAllDrivers() {
        var drivers = driverRepository.findAll();

        return drivers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DriverDTO> getDriverById(Long id) {
        return driverRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Long createDriver(DriverDTO driverDTO) {
        Driver driver = convertToEntity(driverDTO);
        return driverRepository.create(driver);
    }

    public DriverDTO updateDriver(DriverDTO driverDTO) {
        Driver driver = convertToEntity(driverDTO);
        Driver savedDriver = driverRepository.update(driver);
        return convertToDTO(savedDriver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
