package ba.unsa.etf.nbp.VehicleTrackPlatform.mappings;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.UserDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.User;

public class UserMapping {
    public static UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getPhoneNumber(),
                user.getBirthDate(),
                user.getRoleId()
        );
    }

    public static User convertToEntity(UserDTO dto) {
        return new User(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getUsername(),
                dto.getPhoneNumber(),
                dto.getBirthDate(),
                dto.getRoleId()
        );
    }
}
