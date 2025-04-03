package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.StationDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Station;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StationService {
    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    private StationDTO convertToDTO(Station station) {
        StationDTO dto = new StationDTO();
        dto.setId(station.getId());
        dto.setName(station.getName());
        dto.setAddress(station.getAddress());
        dto.setCity(station.getCity());
        dto.setPostalCode(station.getPostalCode());
        dto.setPhoneNumber(station.getPhoneNumber());
        dto.setEmail(station.getEmail());
        return dto;
    }

    private Station convertToEntity(StationDTO dto) {
        Station station = new Station();
        station.setId(dto.getId());
        station.setName(dto.getName());
        station.setAddress(dto.getAddress());
        station.setCity(dto.getCity());
        station.setPostalCode(dto.getPostalCode());
        station.setPhoneNumber(dto.getPhoneNumber());
        station.setEmail(dto.getEmail());
        return station;
    }

    public List<StationDTO> getAllStations() {
        return stationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<StationDTO> getStationById(Long id) {
        return stationRepository.findById(id)
                .map(this::convertToDTO);
    }

    public StationDTO saveStation(StationDTO stationDTO) {
        Station station = convertToEntity(stationDTO);
        Station savedStation = stationRepository.save(station);
        return convertToDTO(savedStation);
    }

    public void deleteStation(Long id) {
        stationRepository.deleteById(id);
    }
}
