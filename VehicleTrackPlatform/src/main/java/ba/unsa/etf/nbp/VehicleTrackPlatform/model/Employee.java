package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

public class Employee extends AuditableEntity {
    private Long id;
    private Long companyId;
    private Long userId;
    private Company company;
    private User user;

    public Employee() {
    }

    public Employee(Long id, Long companyId, Long userId) {
        this.id = id;
        this.userId = userId;
        this.companyId = companyId;
    }

    public Employee(Long id, Company company, User user) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
