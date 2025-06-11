package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.ServiceRequestDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.mappings.ServiceRequestMapping;
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

    public List<ServiceRequestDTO> getAllServiceRequests() {
        var requests = serviceRequestRepository.findAll();

        return requests.stream()
                .map(ServiceRequestMapping::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ServiceRequestDTO> getServiceRequestById(Long id) {
        return serviceRequestRepository.findById(id)
                .map(ServiceRequestMapping::convertToDTO);
    }

    public Long createServiceRequest(ServiceRequestDTO dto) {
        ServiceRequest request = ServiceRequestMapping.convertToEntity(dto);
        return serviceRequestRepository.create(request);
    }

    public ServiceRequestDTO updateServiceRequest(ServiceRequestDTO dto) {
        ServiceRequest request = ServiceRequestMapping.convertToEntity(dto);
        ServiceRequest savedRequest = serviceRequestRepository.update(request);
        return ServiceRequestMapping.convertToDTO(savedRequest);
    }

    public List<ServiceRequestDTO> getServiceRequestsByServicerId(Long servicerId) {
        var requests = serviceRequestRepository.findByServicerId(servicerId);
        return requests.stream()
                .map(ServiceRequestMapping::convertToDTO)
                .collect(Collectors.toList());
    }


    public void deleteServiceRequest(Long id) {
        serviceRequestRepository.deleteById(id);
    }
}
