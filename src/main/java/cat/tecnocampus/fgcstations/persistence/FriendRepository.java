package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.FriendUserDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFavoriteJourney;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFriend;
import cat.tecnocampus.fgcstations.domain.Friend;
import cat.tecnocampus.fgcstations.domain.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {

List<Friend> findByUserName(String userName);


@Query ("SELECT u.username, u name, u.secondName, COUNT (f) AS numberOfFriends " +
        "FROM Friend f " +
        "JOIN f.user u " +
        "GROUP BY u.username,u.name,u.secondName "+
        "ORDER BY  COUNT(f) DESC")
    List <UserTopFriend> findTop3UsersWithMostFriends();

    @Query("SELECT f.user.username AS userUsername, f.user.name AS userName " +
            "FROM Friend f WHERE f.id.friend = :friendName")
    List <FriendUserDTO>findUsersByFriendName(String friendName);

    /*@Query ("SELECT u.username, u.name, u.secondName, COUNT (f) AS numberOfFavoriteJourneys " +
            "FROM FavoriteJourney f  " +
            "JOIN f.user u " +
            "GROUP BY  u.username,u.name, u.secondName " +
            "ORDER BY COUNT(f) DESC")
    List <UserTopFavoriteJourney> findTop3UsersWithMostJourneys();

     */


}
