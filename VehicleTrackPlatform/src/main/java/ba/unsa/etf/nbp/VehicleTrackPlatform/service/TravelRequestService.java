package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.TravelRequestDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.TravelRequest;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.TravelRequestStatus;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.TravelRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TravelRequestService {
    private final TravelRequestRepository travelRequestRepository;

    @Autowired
    public TravelRequestService(TravelRequestRepository travelRequestRepository) {
        this.travelRequestRepository = travelRequestRepository;
    }

    private TravelRequestDTO convertToDTO(TravelRequest travelRequest) {
        var dto = new TravelRequestDTO(
                travelRequest.getId(),
                travelRequest.getApprovedBy(),
                travelRequest.getApprovalDate(),
                travelRequest.getDepartureLocation(),
                travelRequest.getDestination(),
                travelRequest.getStartDate(),
                travelRequest.getEndDate(),
                travelRequest.getRequestStatus(),
                travelRequest.getVehicleId(),
                travelRequest.getDriverId()
        );

        dto.setCreatedAt(travelRequest.getCreatedAt());
        dto.setCreatedBy(travelRequest.getCreatedBy());
        dto.setModifiedAt(travelRequest.getModifiedAt());
        dto.setModifiedBy(travelRequest.getModifiedBy());

        return dto;
    }

    private TravelRequest convertToEntity(TravelRequestDTO dto) {
        return new TravelRequest(
                dto.getId(),
                dto.getApprovedBy(),
                dto.getApprovalDate(),
                dto.getDepartureLocation(),
                dto.getDestination(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getRequestStatus(),
                dto.getVehicleId(),
                dto.getDriverId()
        );
    }

    public List<TravelRequestDTO> getAllTravelRequests() {
        var travelRequests = travelRequestRepository.findAll();

        return travelRequests.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TravelRequestDTO> getTravelRequestById(Long id) {
        return travelRequestRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Long createTravelRequest(TravelRequestDTO travelRequestDTO) {
        TravelRequest travelRequest = convertToEntity(travelRequestDTO);
        return travelRequestRepository.create(travelRequest);
    }

    public TravelRequestDTO updateTravelRequest(TravelRequestDTO travelRequestDTO) {
        TravelRequest travelRequest = convertToEntity(travelRequestDTO);
        TravelRequest savedTravelRequest = travelRequestRepository.update(travelRequest);
        return convertToDTO(savedTravelRequest);
    }

    public void deleteTravelRequest(Long id) {
        travelRequestRepository.deleteById(id);
    }
}
