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
        var dto = new VehicleDTO(
                vehicle.getId(),
                vehicle.getLicensePlate(),
                vehicle.getFirstRegistrationDate(),
                vehicle.getFirstRegistrationPlace(),
                vehicle.getFirstLicensePlate(),
                vehicle.getRegistrationIssueDate(),
                vehicle.getRegistrationIssuePlace(),
                vehicle.getFuelId(),
                vehicle.getVehicleCategory(),
                vehicle.getVehicleBodyType(),
                vehicle.getColor(),
                vehicle.getVehicleBrandType(),
                vehicle.getRegistrationNumber(),
                vehicle.getCommercialDescription(),
                vehicle.getChassisNumber(),
                vehicle.getProductionYear(),
                vehicle.getMaxWeight(),
                vehicle.getPayload(),
                vehicle.getVehicleWeight(),
                vehicle.getPowerWeightRatio(),
                vehicle.getSeatCount(),
                vehicle.getEngineDisplacement(),
                vehicle.getMaxPower(),
                vehicle.getEcoCharacteristics(),
                vehicle.getCatalyst(),
                vehicle.getEngineNumber()
        );

        dto.setCreatedAt(vehicle.getCreatedAt());
        dto.setCreatedBy(vehicle.getCreatedBy());
        dto.setModifiedAt(vehicle.getModifiedAt());
        dto.setModifiedBy(vehicle.getModifiedBy());

        return dto;
    }

    private Vehicle convertToEntity(VehicleDTO dto) {
        return new Vehicle(
                dto.getId(),
                dto.getLicensePlate(),
                dto.getFirstRegistrationDate(),
                dto.getFirstRegistrationPlace(),
                dto.getFirstLicensePlate(),
                dto.getRegistrationIssueDate(),
                dto.getRegistrationIssuePlace(),
                dto.getFuelId(),
                dto.getVehicleCategory(),
                dto.getVehicleBodyType(),
                dto.getColor(),
                dto.getVehicleBrandType(),
                dto.getRegistrationNumber(),
                dto.getCommercialDescription(),
                dto.getChassisNumber(),
                dto.getProductionYear(),
                dto.getMaxWeight(),
                dto.getPayload(),
                dto.getVehicleWeight(),
                dto.getPowerWeightRatio(),
                dto.getSeatCount(),
                dto.getEngineDisplacement(),
                dto.getMaxPower(),
                dto.getEcoCharacteristics(),
                dto.getCatalyst(),
                dto.getEngineNumber()
        );
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

    public Long createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = convertToEntity(vehicleDTO);
        return vehicleRepository.create(vehicle);
    }

    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = convertToEntity(vehicleDTO);
        Vehicle savedVehicle = vehicleRepository.update(vehicle);
        return convertToDTO(savedVehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
