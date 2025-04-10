package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.VehicleDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Vehicle;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    private VehicleDTO convertToDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setLicensePlate(vehicle.getLicensePlate());
        dto.setFirstRegistrationDate(vehicle.getFirstRegistrationDate());
        dto.setFirstRegistrationPlace(vehicle.getFirstRegistrationPlace());
        dto.setFirstLicensePlate(vehicle.getFirstLicensePlate());
        dto.setRegistrationIssueDate(vehicle.getRegistrationIssueDate());
        dto.setRegistrationIssuePlace(vehicle.getRegistrationIssuePlace());
        dto.setFuelId(vehicle.getFuelId());
        dto.setVehicleCategory(vehicle.getVehicleCategory());
        dto.setVehicleBodyType(vehicle.getVehicleBodyType());
        dto.setColor(vehicle.getColor());
        dto.setVehicleBrandType(vehicle.getVehicleBrandType());
        dto.setRegistrationNumber(vehicle.getRegistrationNumber());
        dto.setCommercialDescription(vehicle.getCommercialDescription());
        dto.setChassisNumber(vehicle.getChassisNumber());
        dto.setProductionYear(vehicle.getProductionYear());
        dto.setMaxWeight(vehicle.getMaxWeight());
        dto.setPayload(vehicle.getPayload());
        dto.setVehicleWeight(vehicle.getVehicleWeight());
        dto.setPowerWeightRatio(vehicle.getPowerWeightRatio());
        dto.setSeatCount(vehicle.getSeatCount());
        dto.setEngineDisplacement(vehicle.getEngineDisplacement());
        dto.setMaxPower(vehicle.getMaxPower());
        dto.setEcoCharacteristics(vehicle.getEcoCharacteristics());
        dto.setCatalyst(vehicle.getCatalyst());
        dto.setEngineNumber(vehicle.getEngineNumber());
        return dto;
    }

    private Vehicle convertToEntity(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(dto.getId());
        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setFirstRegistrationDate(dto.getFirstRegistrationDate());
        vehicle.setFirstRegistrationPlace(dto.getFirstRegistrationPlace());
        vehicle.setFirstLicensePlate(dto.getFirstLicensePlate());
        vehicle.setRegistrationIssueDate(dto.getRegistrationIssueDate());
        vehicle.setRegistrationIssuePlace(dto.getRegistrationIssuePlace());
        vehicle.setFuelId(dto.getFuelId());
        vehicle.setVehicleCategory(dto.getVehicleCategory());
        vehicle.setVehicleBodyType(dto.getVehicleBodyType());
        vehicle.setColor(dto.getColor());
        vehicle.setVehicleBrandType(dto.getVehicleBrandType());
        vehicle.setRegistrationNumber(dto.getRegistrationNumber());
        vehicle.setCommercialDescription(dto.getCommercialDescription());
        vehicle.setChassisNumber(dto.getChassisNumber());
        vehicle.setProductionYear(dto.getProductionYear());
        vehicle.setMaxWeight(dto.getMaxWeight());
        vehicle.setPayload(dto.getPayload());
        vehicle.setVehicleWeight(dto.getVehicleWeight());
        vehicle.setPowerWeightRatio(dto.getPowerWeightRatio());
        vehicle.setSeatCount(dto.getSeatCount());
        vehicle.setEngineDisplacement(dto.getEngineDisplacement());
        vehicle.setMaxPower(dto.getMaxPower());
        vehicle.setEcoCharacteristics(dto.getEcoCharacteristics());
        vehicle.setCatalyst(dto.getCatalyst());
        vehicle.setEngineNumber(dto.getEngineNumber());
        return vehicle;
    }

    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<VehicleDTO> getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(this::convertToDTO);
    }

    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = convertToEntity(vehicleDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return convertToDTO(savedVehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
