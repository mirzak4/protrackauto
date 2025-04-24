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
        RefuelDTO dto = new RefuelDTO(
                refuel.getId(),
                refuel.getFiscalReceiptNumber(),
                refuel.getRefuelDate(),
                refuel.getQuantity(),
                refuel.getTotalChargeAmount(),
                refuel.getGasStationId(),
                refuel.getVehicleId()
        );

        dto.setCreatedAt(refuel.getCreatedAt());
        dto.setCreatedBy(refuel.getCreatedBy());
        dto.setModifiedAt(refuel.getModifiedAt());
        dto.setModifiedBy(refuel.getModifiedBy());

        return dto;
    }

    private Refuel convertToEntity(RefuelDTO dto) {
        return new Refuel(
                dto.getId(),
                dto.getFiscalReceiptNumber(),
                dto.getRefuelDate(),
                dto.getQuantity(),
                dto.getTotalChargeAmount(),
                dto.getGasStationId(),
                dto.getVehicleId()
        );
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

    public Long createRefuel(RefuelDTO refuelDTO) {
        Refuel refuel = convertToEntity(refuelDTO);
        return refuelRepository.create(refuel);
    }

    public RefuelDTO updateRefuel(RefuelDTO refuelDTO) {
        Refuel refuel = convertToEntity(refuelDTO);
        Refuel savedRefuel = refuelRepository.update(refuel);
        return convertToDTO(savedRefuel);
    }

    public void deleteRefuel(Long id) {
        refuelRepository.deleteById(id);
    }
}
