package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

public class GasStationFuelPriceReport {
    private long id;
    private int companyId;
    private String name;
    private int documentId;

    public GasStationFuelPriceReport(long id, int companyId, String name, int documentId) {
        this.id = id;
        this.companyId = companyId;
        this.name = name;
        this.documentId = documentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }
}
