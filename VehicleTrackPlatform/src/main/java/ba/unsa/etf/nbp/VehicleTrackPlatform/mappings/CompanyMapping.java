package ba.unsa.etf.nbp.VehicleTrackPlatform.mappings;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.CompanyDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Company;

public class CompanyMapping {
    public static CompanyDTO convertToDTO(Company company) {
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

    public static Company convertToEntity(CompanyDTO dto) {
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
}
