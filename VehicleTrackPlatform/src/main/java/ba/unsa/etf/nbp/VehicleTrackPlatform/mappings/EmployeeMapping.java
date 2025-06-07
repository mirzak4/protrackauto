package ba.unsa.etf.nbp.VehicleTrackPlatform.mappings;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.EmployeeDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.UserDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Employee;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.User;

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
        User user = null;
        UserDTO userDTO = dto.getUser();
        if (userDTO != null) {
            user = new User();
            user.setId(userDTO.getId());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setUsername(userDTO.getUsername());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setBirthDate(userDTO.getBirthDate());
            user.setRoleId(userDTO.getRoleId());
        }

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setCompanyId(dto.getCompanyId());
        employee.setUser(user);
        employee.setUserId(user != null ? user.getId() : dto.getUserId());

        return employee;
    }

}
