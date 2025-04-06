package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.RefuelDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Refuel;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.RefuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RefuelService {
    private final RefuelRepository refuelRepository;

    @Autowired
    public RefuelService(RefuelRepository refuelRepository) {
        this.refuelRepository = refuelRepository;
    }

    private RefuelDTO convertToDTO(Refuel refuel) {
        RefuelDTO dto = new RefuelDTO();
        dto.setId(refuel.getId());
        dto.setFiscalReceiptNumber(refuel.getFiscalReceiptNumber());
        dto.setRefuelDate(refuel.getRefuelDate());
        dto.setQuantity(refuel.getQuantity());
        dto.setPricePerLiter(refuel.getPricePerLiter());
        dto.setMileage(refuel.getMileage());
        dto.setFuelTypeId(refuel.getFuelTypeId());
        dto.setStationId(refuel.getStationId());
        dto.setVehicleId(refuel.getVehicleId());
        return dto;
    }

    private Refuel convertToEntity(RefuelDTO dto) {
        Refuel refuel = new Refuel();
        refuel.setId(dto.getId());
        refuel.setFiscalReceiptNumber(dto.getFiscalReceiptNumber());
        refuel.setRefuelDate(dto.getRefuelDate());
        refuel.setQuantity(dto.getQuantity());
        refuel.setPricePerLiter(dto.getPricePerLiter());
        refuel.setMileage(dto.getMileage());
        refuel.setFuelTypeId(dto.getFuelTypeId());
        refuel.setStationId(dto.getStationId());
        refuel.setVehicleId(dto.getVehicleId());
        return refuel;
    }

    public List<RefuelDTO> getAllRefuels() {
        return refuelRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<RefuelDTO> getRefuelById(Long id) {
        return refuelRepository.findById(id)
                .map(this::convertToDTO);
    }

    public RefuelDTO saveRefuel(RefuelDTO refuelDTO) {
        Refuel refuel = convertToEntity(refuelDTO);
        Refuel savedRefuel = refuelRepository.save(refuel);
        return convertToDTO(savedRefuel);
    }

    public void deleteRefuel(Long id) {
        refuelRepository.deleteById(id);
    }

    public List<RefuelDTO> getRefuelsByVehicle(Long vehicleId) {
        return refuelRepository.findByVehicleId(vehicleId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<RefuelDTO> getRefuelsByStation(Long stationId) {
        return refuelRepository.findByStationId(stationId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
