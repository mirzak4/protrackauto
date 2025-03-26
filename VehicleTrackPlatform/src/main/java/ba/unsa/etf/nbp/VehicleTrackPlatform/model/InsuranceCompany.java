package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

public class InsuranceCompany {
    private Long id;
    private String companyName;
    private String address;
    private String email;
    private String contactPerson;

    public InsuranceCompany() {
    }

    public InsuranceCompany(Long id, String companyName, String address, String email, String contactPerson) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.contactPerson = contactPerson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}
