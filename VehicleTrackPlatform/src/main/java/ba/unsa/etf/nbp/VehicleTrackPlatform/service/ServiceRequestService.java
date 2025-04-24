package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.ServiceRequestDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.ServiceRequest;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceRequestService {
    private final ServiceRequestRepository serviceRequestRepository;

    @Autowired
    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    private ServiceRequestDTO convertToDTO(ServiceRequest request) {
        var dto = new ServiceRequestDTO(
                request.getId(),
                request.getServiceType(),
                request.getFiscalReceiptNumber(),
                request.getCost(),
                request.getStatus(),
                request.getRequestDate(),
                request.getRequestedBy(),
                request.getVehicleId(),
                request.getServicerId()
        );

        dto.setCreatedAt(request.getCreatedAt());
        dto.setCreatedBy(request.getCreatedBy());
        dto.setModifiedAt(request.getModifiedAt());
        dto.setModifiedBy(request.getModifiedBy());

        return dto;
    }

    private ServiceRequest convertToEntity(ServiceRequestDTO dto) {
        return new ServiceRequest(
                dto.getId(),
                dto.getServiceType(),
                dto.getFiscalReceiptNumber(),
                dto.getCost(),
                dto.getStatus(),
                dto.getRequestDate(),
                dto.getRequestedBy(),
                dto.getVehicleId(),
                dto.getServicerId()
        );
    }

    public List<ServiceRequestDTO> getAllServiceRequests() {
        var requests = serviceRequestRepository.findAll();

        return requests.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ServiceRequestDTO> getServiceRequestById(Long id) {
        return serviceRequestRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Long createServiceRequest(ServiceRequestDTO dto) {
        ServiceRequest request = convertToEntity(dto);
        return serviceRequestRepository.create(request);
    }

    public ServiceRequestDTO updateServiceRequest(ServiceRequestDTO dto) {
        ServiceRequest request = convertToEntity(dto);
        ServiceRequest savedRequest = serviceRequestRepository.update(request);
        return convertToDTO(savedRequest);
    }

    public void deleteServiceRequest(Long id) {
        serviceRequestRepository.deleteById(id);
    }
}
