package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Document;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.GasStationFuelPriceReport;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.DocumentRepository;
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

import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final DocumentRepository documentRepository;
    private final GasStationFuelPriceReportRepository gasStationFuelPriceReportRepository;

    @Autowired
    public ReportController(
            DocumentRepository documentRepository,
            GasStationFuelPriceReportRepository gasStationFuelPriceReportRepository
    ) {
        this.documentRepository = documentRepository;
        this.gasStationFuelPriceReportRepository = gasStationFuelPriceReportRepository;
    }

    @Operation(summary = "Get document content by report ID", description = "Returns the PDF document content for the specified report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Document retrieved successfully", content = @Content(
                    mediaType = "application/pdf",
                    schema = @Schema(type = "string", format = "binary")
            )),
            @ApiResponse(responseCode = "403", description = "Not authorized to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Report or document not found", content = @Content)
    })
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    @GetMapping("/{reportId}/document")
    public ResponseEntity<byte[]> getReportDocument(@PathVariable Long reportId) {
        Optional<GasStationFuelPriceReport> report = gasStationFuelPriceReportRepository.findById(reportId);
        
        if (report.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Document> document = documentRepository.findById(Long.valueOf(report.get().getDocumentId()));
        
        if (document.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", document.get().getFileName());
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(document.get().getContent());
    }
} 