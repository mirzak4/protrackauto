package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.TravelRequestDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.TravelRequest;
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

    private TravelRequestDTO convertToDTO(TravelRequest request) {
        TravelRequestDTO dto = new TravelRequestDTO();
        dto.setId(request.getId());
        dto.setApprovedBy(request.getApprovedBy());
        dto.setApprovalDate(request.getApprovalDate());
        dto.setDepartureLocation(request.getDepartureLocation());
        dto.setDestination(request.getDestination());
        dto.setStartDate(request.getStartDate());
        dto.setEndDate(request.getEndDate());
        dto.setRequestStatus(request.getRequestStatus());
        dto.setVehicleId(request.getVehicleId());
        dto.setDriverId(request.getDriverId());
        return dto;
    }

    private TravelRequest convertToEntity(TravelRequestDTO dto) {
        TravelRequest request = new TravelRequest();
        request.setId(dto.getId());
        request.setApprovedBy(dto.getApprovedBy());
        request.setApprovalDate(dto.getApprovalDate());
        request.setDepartureLocation(dto.getDepartureLocation());
        request.setDestination(dto.getDestination());
        request.setStartDate(dto.getStartDate());
        request.setEndDate(dto.getEndDate());
        request.setRequestStatus(dto.getRequestStatus());
        request.setVehicleId(dto.getVehicleId());
        request.setDriverId(dto.getDriverId());
        return request;
    }

    public List<TravelRequestDTO> getAllTravelRequests() {
        return travelRequestRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TravelRequestDTO> getTravelRequestById(Long id) {
        return travelRequestRepository.findById(id)
                .map(this::convertToDTO);
    }

    public TravelRequestDTO saveTravelRequest(TravelRequestDTO travelRequestDTO) {
        TravelRequest request = convertToEntity(travelRequestDTO);
        TravelRequest savedRequest = travelRequestRepository.save(request);
        return convertToDTO(savedRequest);
    }

    public void deleteTravelRequest(Long id) {
        travelRequestRepository.deleteById(id);
    }
}
