package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.CompanyType;

public class Company {
    private Long id;
    private CompanyType companyType;
    private String name;
    private String address;
    private String email;
    private String contactPerson;

    public Company() {
    }

    public Company(Long id, CompanyType companyType, String name, String address, String email, String contactPerson) {
        this.id = id;
        this.companyType = companyType;
        this.name = name;
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

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
