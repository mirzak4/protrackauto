package ba.unsa.etf.nbp.VehicleTrackPlatform.service;


import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.RegistrationDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Registration;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    private RegistrationDTO convertToDTO(Registration registration) {
        var dto = new RegistrationDTO(
                registration.getId(),
                registration.getPolicyNumber(),
                registration.getInsuranceType(),
                registration.getInsuredFrom(),
                registration.getInsuredUntil(),
                registration.getInsuranceCost(),
                registration.getAdditionalCosts(),
                registration.getVehicleId(),
                registration.getInsuranceCompanyId()
        );

        dto.setCreatedAt(registration.getCreatedAt());
        dto.setCreatedBy(registration.getCreatedBy());
        dto.setModifiedAt(registration.getModifiedAt());
        dto.setModifiedBy(registration.getModifiedBy());

        return dto;
    }


    private Registration convertToEntity(RegistrationDTO dto) {
        return new Registration(
                dto.getId(),
                dto.getPolicyNumber(),
                dto.getInsuranceType(),
                dto.getInsuredFrom(),
                dto.getInsuredUntil(),
                dto.getInsuranceCost(),
                dto.getAdditionalCosts(),
                dto.getVehicleId(),
                dto.getInsuranceCompanyId()
        );
    }

    public List<RegistrationDTO> getAllRegistrations() {
        return registrationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<RegistrationDTO> getRegistrationById(Long id) {
        return registrationRepository.findById(id).map(this::convertToDTO);
    }

    public Long createRegistration(RegistrationDTO registrationDTO) {
        return registrationRepository.create(convertToEntity(registrationDTO));
    }

    public RegistrationDTO updateRegistration(RegistrationDTO registrationDTO) {
        return convertToDTO(registrationRepository.update(convertToEntity(registrationDTO)));
    }

    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }
}
