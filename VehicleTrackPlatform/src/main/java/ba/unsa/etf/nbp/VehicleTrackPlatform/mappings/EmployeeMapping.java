package ba.unsa.etf.nbp.VehicleTrackPlatform.mappings;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.EmployeeDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Employee;

public class EmployeeMapping {

    public static EmployeeDTO convertToDTO(Employee employee) {

        EmployeeDTO dto = employee.getUser() != null && employee.getCompany() != null ? new EmployeeDTO(
                employee.getId(),
                CompanyMapping.convertToDTO(employee.getCompany()),
                UserMapping.convertToDTO(employee.getUser())
        ) : new EmployeeDTO(
                employee.getId(),
                employee.getCompanyId(),
                employee.getUserId()
        );

        dto.setCreatedAt(employee.getCreatedAt());
        dto.setCreatedBy(employee.getCreatedBy());
        dto.setModifiedAt(employee.getModifiedAt());
        dto.setModifiedBy(employee.getModifiedBy());

        return dto;
    }

    public static Employee convertToEntity(EmployeeDTO dto) {
        Employee employee = dto.getUser() != null && dto.getCompany() != null ? new Employee(
                dto.getId(),
                CompanyMapping.convertToEntity(dto.getCompany()),
                UserMapping.convertToEntity(dto.getUser())
        ) : new Employee(
                dto.getId(),
                dto.getCompanyId(),
                dto.getUserId()
        );

        return employee;
    }
}
