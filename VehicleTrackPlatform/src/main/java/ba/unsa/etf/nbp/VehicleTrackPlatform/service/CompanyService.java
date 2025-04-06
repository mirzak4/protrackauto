package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.CompanyDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Company;
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
        CompanyDTO dto = new CompanyDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setAddress(company.getAddress());
        dto.setEmail(company.getEmail());
        return dto;
    }

    private Company convertToEntity(CompanyDTO dto) {
        Company company = new Company();
        company.setId(dto.getId());
        company.setName(dto.getName());
        company.setAddress(dto.getAddress());
        company.setEmail(dto.getEmail());
        return company;
    }

    public List<CompanyDTO> getAllCompanies() {
        var companies = companyRepository.findAll();

        return companies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CompanyDTO> getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(this::convertToDTO);
    }

    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        Company company = convertToEntity(companyDTO);
        Company savedCompany = companyRepository.save(company);
        return convertToDTO(savedCompany);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
