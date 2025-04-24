package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.FuelDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Fuel;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuelService {
    private final FuelRepository fuelRepository;

    @Autowired
    public FuelService(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    private FuelDTO convertToDTO(Fuel fuel) {
        FuelDTO dto = new FuelDTO(fuel.getId(), fuel.getName());
        dto.setCreatedAt(fuel.getCreatedAt());
        dto.setCreatedBy(fuel.getCreatedBy());
        dto.setModifiedAt(fuel.getModifiedAt());
        dto.setModifiedBy(fuel.getModifiedBy());
        return dto;
    }

    private Fuel convertToEntity(FuelDTO dto) {
        Fuel fuel = new Fuel(dto.getId(), dto.getName());
        fuel.setCreatedAt(dto.getCreatedAt());
        fuel.setCreatedBy(dto.getCreatedBy());
        fuel.setModifiedAt(dto.getModifiedAt());
        fuel.setModifiedBy(dto.getModifiedBy());
        return fuel;
    }

    public List<FuelDTO> getAllFuels() {
        return fuelRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FuelDTO> getFuelById(Long id) {
        return fuelRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Long createFuel(FuelDTO fuelDTO) {
        Fuel fuel = convertToEntity(fuelDTO);
        return fuelRepository.create(fuel);
    }

    public FuelDTO updateFuel(FuelDTO fuelDTO) {
        Fuel fuel = convertToEntity(fuelDTO);
        Fuel updated = fuelRepository.update(fuel);
        return convertToDTO(updated);
    }

    public void deleteFuel(Long id) {
        fuelRepository.deleteById(id);
    }
}
