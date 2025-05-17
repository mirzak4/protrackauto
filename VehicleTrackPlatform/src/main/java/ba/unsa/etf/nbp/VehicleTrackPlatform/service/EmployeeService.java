package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.common.authentication.Roles;
import ba.unsa.etf.nbp.VehicleTrackPlatform.common.authentication.VerificationCodeGenerator;
import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.EmployeeDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.mappings.EmployeeMapping;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Employee;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.EmployeeRepository;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.RoleRepository;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final NotificationService notificationService;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    public EmployeeService(NotificationService notificationService, EmployeeRepository employeeRepository,
                           RoleRepository roleRepository) {
        this.notificationService = notificationService;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        var employees = employeeRepository.findAll();

        return employees.stream()
                .map(EmployeeMapping::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(EmployeeMapping::convertToDTO);
    }

    public Long createEmployee(EmployeeDTO employeeDTO) throws MessagingException {
        Employee employee = EmployeeMapping.convertToEntity(employeeDTO);

        //String roleName = employeeDTO.getUser().getRole();
        //var role = roleRepository.findRoleByName(roleName.toUpperCase());
        //employee.getUser().setRoleId(role.getId());

        employee.getUser().setActive(false);

        String verificationCode = VerificationCodeGenerator.generateCode();
        employee.getUser().setLastVerificationCode(verificationCode);

        var createdEmployeeId = employeeRepository.create(employee);
        this.notificationService.sendAccountCreatedNotification(employee.getUser().getEmail(),
                employee.getUser().getFirstName(), verificationCode);

        return createdEmployeeId;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapping.convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.update(employee);
        return EmployeeMapping.convertToDTO(savedEmployee);
    }

    public void deleteEmployee(Long userId) {
        employeeRepository.deleteByUserId(userId);
    }

}
