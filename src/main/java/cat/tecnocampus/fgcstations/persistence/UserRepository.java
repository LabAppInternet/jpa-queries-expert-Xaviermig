package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.UserDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserDTOInterface;
import cat.tecnocampus.fgcstations.application.DTOs.UserDTOnoFJ;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFavoriteJourney;
import cat.tecnocampus.fgcstations.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

 Optional <User> findByName (String username);





    @Query ("SELECT u FROM User u")
    List <User> getAllUsers ();

    @Query ("SELECT u.username, u.name, u.secondName, COUNT (f) AS numberOfFavoriteJourneys " +
            "FROM FavoriteJourney f  " +
            "JOIN f.user u " +
            "GROUP BY  u.username,u.name, u.secondName " +
            "ORDER BY COUNT(f) DESC")
    List <UserTopFavoriteJourney> findTop3UsersWithMostJourneys();





}
