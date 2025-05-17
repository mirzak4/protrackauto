package ba.unsa.etf.nbp.VehicleTrackPlatform.dto;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.AuditableEntity;

public class EmployeeDTO extends AuditableEntity {
    private Long id;
    private Long companyId;
    private Long userId;

    private CompanyDTO company;
    private UserDTO user;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, Long companyId, Long userId) {
        this.id = id;
        this.companyId = companyId;
        this.userId = userId;
    }

    public EmployeeDTO(Long id, CompanyDTO company, UserDTO user) {
        this.id = id;

        this.company = company;
        this.companyId = company.getId();

        this.user = user;
        this.userId = user.getId();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
