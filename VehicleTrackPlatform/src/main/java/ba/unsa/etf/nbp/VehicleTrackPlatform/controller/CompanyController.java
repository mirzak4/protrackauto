package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.CompanyDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.CompanyType;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.CompanyService;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.ReportService;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.GasStationFuelPriceReport;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.GasStationFuelPriceReportRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final ReportService reportService;
    private final GasStationFuelPriceReportRepository gasStationFuelPriceReportRepository;

    @Autowired
    public CompanyController(
            CompanyService companyService,
            ReportService reportService,
            GasStationFuelPriceReportRepository gasStationFuelPriceReportRepository
    ) {
        this.companyService = companyService;
        this.reportService = reportService;
        this.gasStationFuelPriceReportRepository = gasStationFuelPriceReportRepository;
    }

    @Operation(summary = "Get all companies by type", description = "Returns all companies with the provided type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of companies"),
            @ApiResponse(responseCode = "400", description = "Invalid company type supplied", content = @Content),
    })
    @GetMapping
    @PreAuthorize("hasAnyAuthority(@roles.ADMIN, @roles.CLAIMS_ADJUSTER, @roles.FIELD_TECHNICIAN, @roles.STATION_MANAGER)")
    public List<CompanyDTO> getAllCompanies(@RequestParam CompanyType companyType) {
        return companyService.getAllCompanies(companyType);
    }

    @Operation(summary = "Get company by id", description = "Returns the company if it exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company found"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN) or hasAuthority(@roles.FIELD_TECHNICIAN)")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new company", description = "Returns the id of newly created company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Company created"),
            @ApiResponse(responseCode = "400", description = "Invalid company payload supplied", content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<Long> createCompany(@RequestBody CompanyDTO companyDTO) {
        Long companyId = companyService.createCompany(companyDTO);
        return ResponseEntity.created(URI.create("/api/company/" + companyId))
                .body(companyId);
    }

    @Operation(summary = "Update existing company", description = "Returns the updated company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid company payload supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        companyDTO.setId(id);
        return ResponseEntity.ok(companyService.updateCompany(companyDTO));
    }

    @Operation(summary = "Delete company by id", description = "Deletes company with provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Company deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Generate and store weekly fuel price report",
            description = "Generates a PDF report of weekly fuel prices for a company, stores it in the database, and returns the PDF content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report generated and stored successfully", content = @Content(
                    mediaType = "application/pdf",
                    schema = @Schema(type = "string", format = "binary")
            )),
            @ApiResponse(responseCode = "403", description = "Not authorized to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error generating report", content = @Content)
    })
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    @PostMapping("/{companyId}/reports/weekly-fuel-prices")
    public ResponseEntity<byte[]> generateWeeklyFuelPriceReport(@PathVariable Long companyId) {
        try {
            byte[] report = reportService.generateWeeklyFuelPriceReport(companyId);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "weekly-fuel-prices.pdf");
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(report);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get all fuel price reports for a company", description = "Returns a list of all fuel price reports for the specified company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reports retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Not authorized to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
    })
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    @GetMapping("/{companyId}/reports")
    public ResponseEntity<List<GasStationFuelPriceReport>> getCompanyReports(@PathVariable Long companyId) {
        List<GasStationFuelPriceReport> reports = gasStationFuelPriceReportRepository.findByCompanyId(companyId);
        return ResponseEntity.ok(reports);
    }
}
