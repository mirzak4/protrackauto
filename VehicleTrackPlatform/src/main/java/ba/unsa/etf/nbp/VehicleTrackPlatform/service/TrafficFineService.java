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

    private TrafficFineDTO convertToDTO(TrafficFine fine) {
        TrafficFineDTO dto = new TrafficFineDTO();
        dto.setId(fine.getId());
        dto.setIssueDate(fine.getIssueDate());
        dto.setPaymentDueDate(fine.getPaymentDueDate());
        dto.setViolationDescription(fine.getViolationDescription());
        dto.setViolationType(fine.getViolationType());
        dto.setLocation(fine.getLocation());
        dto.setPaymentStatus(fine.getPaymentStatus());
        dto.setAmount(fine.getAmount());
        dto.setVehicleId(fine.getVehicleId());
        dto.setDriverId(fine.getDriverId());
        return dto;
    }

    private TrafficFine convertToEntity(TrafficFineDTO dto) {
        TrafficFine fine = new TrafficFine();
        fine.setId(dto.getId());
        fine.setIssueDate(dto.getIssueDate());
        fine.setPaymentDueDate(dto.getPaymentDueDate());
        fine.setViolationDescription(dto.getViolationDescription());
        fine.setViolationType(dto.getViolationType());
        fine.setLocation(dto.getLocation());
        fine.setPaymentStatus(dto.getPaymentStatus());
        fine.setAmount(dto.getAmount());
        fine.setVehicleId(dto.getVehicleId());
        fine.setDriverId(dto.getDriverId());
        return fine;
    }

    public List<TrafficFineDTO> getAllTrafficFines() {
        return trafficFineRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TrafficFineDTO> getTrafficFineById(Long id) {
        return trafficFineRepository.findById(id)
                .map(this::convertToDTO);
    }

    public TrafficFineDTO saveTrafficFine(TrafficFineDTO trafficFineDTO) {
        TrafficFine fine = convertToEntity(trafficFineDTO);
        TrafficFine savedFine = trafficFineRepository.save(fine);
        return convertToDTO(savedFine);
    }

    public void deleteTrafficFine(Long id) {
        trafficFineRepository.deleteById(id);
    }

    public List<TrafficFineDTO> getFinesByDriver(Long driverId) {
        return trafficFineRepository.findByDriverId(driverId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<TrafficFineDTO> getFinesByVehicle(Long vehicleId) {
        return trafficFineRepository.findByVehicleId(vehicleId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
