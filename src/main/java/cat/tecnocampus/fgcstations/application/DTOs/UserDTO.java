package cat.tecnocampus.fgcstations.application.DTOs;

import java.util.List;

public record UserDTO(String username, String name, String secondName, String email, List<FavoriteJourneyDTO> favoriteJourneyList) implements UserDTOInterface {
    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getSecondName() {
        return "";
    }

    @Override
    public String getEmail() {
        return "";
    }
}
