package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.TrafficFineDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.TrafficFine;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.TrafficFineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrafficFineService {
    private final TrafficFineRepository trafficFineRepository;

    @Autowired
    public TrafficFineService(TrafficFineRepository trafficFineRepository) {
        this.trafficFineRepository = trafficFineRepository;
    }

    private TrafficFineDTO convertToDTO(TrafficFine trafficFine) {
        var dto = new TrafficFineDTO(
                trafficFine.getId(),
                trafficFine.getIssueDate(),
                trafficFine.getPaymentDueDate(),
                trafficFine.getViolationDescription(),
                trafficFine.getViolationType(),
                trafficFine.getLocation(),
                trafficFine.getPaymentStatus(),
                trafficFine.getAmount(),
                trafficFine.getVehicleId(),
                trafficFine.getDriverId()
        );

        dto.setCreatedAt(trafficFine.getCreatedAt());
        dto.setCreatedBy(trafficFine.getCreatedBy());
        dto.setModifiedAt(trafficFine.getModifiedAt());
        dto.setModifiedBy(trafficFine.getModifiedBy());

        return dto;
    }

    private TrafficFine convertToEntity(TrafficFineDTO dto) {
        return new TrafficFine(
                dto.getId(),
                dto.getIssueDate(),
                dto.getPaymentDueDate(),
                dto.getViolationDescription(),
                dto.getViolationType(),
                dto.getLocation(),
                dto.getPaymentStatus(),
                dto.getAmount(),
                dto.getVehicleId(),
                dto.getDriverId()
        );
    }

    public List<TrafficFineDTO> getAllTrafficFines() {
        var fines = trafficFineRepository.findAll();
        return fines.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TrafficFineDTO> getTrafficFineById(Long id) {
        return trafficFineRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Long createTrafficFine(TrafficFineDTO trafficFineDTO) {
        TrafficFine fine = convertToEntity(trafficFineDTO);
        return trafficFineRepository.create(fine);
    }

    public TrafficFineDTO updateTrafficFine(TrafficFineDTO trafficFineDTO) {
        TrafficFine fine = convertToEntity(trafficFineDTO);
        TrafficFine savedFine = trafficFineRepository.update(fine);
        return convertToDTO(savedFine);
    }

    public void deleteTrafficFine(Long id) {
        trafficFineRepository.deleteById(id);
    }
}
