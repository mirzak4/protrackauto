package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.FuelPriceDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.FuelPrice;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.FuelPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuelPriceService {

    private final FuelPriceRepository fuelPriceRepository;

    @Autowired
    public FuelPriceService(FuelPriceRepository fuelPriceRepository) {
        this.fuelPriceRepository = fuelPriceRepository;
    }

    private FuelPriceDTO convertToDTO(FuelPrice fuelPrice) {
        var dto = new FuelPriceDTO(
                fuelPrice.getId(),
                fuelPrice.getPrice(),
                fuelPrice.getIssueDate(),
                fuelPrice.getFuelId(),
                fuelPrice.getGasStationId()
        );

        dto.setCreatedAt(fuelPrice.getCreatedAt());
        dto.setCreatedBy(fuelPrice.getCreatedBy());
        dto.setModifiedAt(fuelPrice.getModifiedAt());
        dto.setModifiedBy(fuelPrice.getModifiedBy());

        return dto;
    }

    private FuelPrice convertToEntity(FuelPriceDTO dto) {
        return new FuelPrice(
                dto.getId(),
                dto.getPrice(),
                dto.getIssueDate(),
                dto.getFuelId(),
                dto.getGasStationId()
        );
    }

    public List<FuelPriceDTO> getAllFuelPrices() {
        var fuelPrices = fuelPriceRepository.findAll();

        return fuelPrices.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FuelPriceDTO> getCompanyFuelPrices(Long companyId) {
        return fuelPriceRepository.findByCompanyId(companyId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FuelPriceDTO> getFuelPriceById(Long id) {
        return fuelPriceRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Long createFuelPrice(FuelPriceDTO fuelPriceDTO) {
        FuelPrice fuelPrice = convertToEntity(fuelPriceDTO);
        return fuelPriceRepository.create(fuelPrice);
    }

    public FuelPriceDTO updateFuelPrice(FuelPriceDTO fuelPriceDTO) {
        FuelPrice fuelPrice = convertToEntity(fuelPriceDTO);
        FuelPrice savedFuelPrice = fuelPriceRepository.update(fuelPrice);
        return convertToDTO(savedFuelPrice);
    }

    public void deleteFuelPrice(Long id) {
        fuelPriceRepository.deleteById(id);
    }
}
