package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.CompanyDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Company;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.CompanyType;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    private CompanyDTO convertToDTO(Company company) {
        var dto = new CompanyDTO(
                company.getId(),
                company.getCompanyType(),
                company.getName(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getContactPerson()
        );

        dto.setCreatedAt(company.getCreatedAt());
        dto.setCreatedBy(company.getCreatedBy());
        dto.setModifiedAt(company.getModifiedAt());
        dto.setModifiedBy(company.getModifiedBy());

        return dto;
    }

    private Company convertToEntity(CompanyDTO dto) {
        return new Company(
                dto.getId(),
                dto.getCompanyType(),
                dto.getName(),
                dto.getAddress(),
                dto.getPhoneNumber(),
                dto.getEmail(),
                dto.getContactPerson()
        );
    }

    public List<CompanyDTO> getAllCompanies(CompanyType companyType) {
        var companies = companyRepository.findAll(companyType);

        return companies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CompanyDTO> getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Long createCompany(CompanyDTO companyDTO) {
        Company company = convertToEntity(companyDTO);
        return companyRepository.create(company);
    }

    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        Company company = convertToEntity(companyDTO);
        Company savedCompany = companyRepository.update(company);
        return convertToDTO(savedCompany);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
