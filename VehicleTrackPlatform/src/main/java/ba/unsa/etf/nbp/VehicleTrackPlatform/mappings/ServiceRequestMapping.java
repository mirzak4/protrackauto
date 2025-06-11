package ba.unsa.etf.nbp.VehicleTrackPlatform.mappings;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.ServiceRequestDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.ServiceRequest;

public class ServiceRequestMapping {

    public static ServiceRequestDTO convertToDTO(ServiceRequest request) {
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

    public static ServiceRequest convertToEntity(ServiceRequestDTO dto) {
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
}
