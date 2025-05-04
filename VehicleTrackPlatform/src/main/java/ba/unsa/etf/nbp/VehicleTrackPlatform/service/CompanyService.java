package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.CompanyDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.mappings.CompanyMapping;
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

    public List<CompanyDTO> getAllCompanies(CompanyType companyType) {
        var companies = companyRepository.findAll(companyType);

        return companies.stream()
                .map(CompanyMapping::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CompanyDTO> getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(CompanyMapping::convertToDTO);
    }

    public Long createCompany(CompanyDTO companyDTO) {
        Company company = CompanyMapping.convertToEntity(companyDTO);
        return companyRepository.create(company);
    }

    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        Company company = CompanyMapping.convertToEntity(companyDTO);
        Company savedCompany = companyRepository.update(company);
        return CompanyMapping.convertToDTO(savedCompany);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
