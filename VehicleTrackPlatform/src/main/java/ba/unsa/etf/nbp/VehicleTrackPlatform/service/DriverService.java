package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.DriverDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Driver;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    private DriverDTO convertToDTO(Driver driver) {
        DriverDTO dto = new DriverDTO();
        dto.setId(driver.getId());
        dto.setFirstName(driver.getFirstName());
        dto.setLastName(driver.getLastName());
        dto.setBirthDate(driver.getBirthDate());
        dto.setLicenseNumber(driver.getLicenseNumber());
        dto.setLicenseExpiry(driver.getLicenseExpiry());
        dto.setAddress(driver.getAddress());
        dto.setPhoneNumber(driver.getPhoneNumber());
        dto.setEmail(driver.getEmail());
        dto.setEmploymentDate(driver.getEmploymentDate());
        dto.setDriverStatus(driver.getDriverStatus());
        return dto;
    }

    private Driver convertToEntity(DriverDTO dto) {
        Driver driver = new Driver();
        driver.setId(dto.getId());
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setBirthDate(dto.getBirthDate());
        driver.setLicenseNumber(dto.getLicenseNumber());
        driver.setLicenseExpiry(dto.getLicenseExpiry());
        driver.setAddress(dto.getAddress());
        driver.setPhoneNumber(dto.getPhoneNumber());
        driver.setEmail(dto.getEmail());
        driver.setEmploymentDate(dto.getEmploymentDate());
        driver.setDriverStatus(dto.getDriverStatus());
        return driver;
    }

    public List<DriverDTO> getAllDrivers() {
        return driverRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DriverDTO> getDriverById(Long id) {
        return driverRepository.findById(id)
                .map(this::convertToDTO);
    }

    public DriverDTO saveDriver(DriverDTO driverDTO) {
        Driver driver = convertToEntity(driverDTO);
        Driver savedDriver = driverRepository.save(driver);
        return convertToDTO(savedDriver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
