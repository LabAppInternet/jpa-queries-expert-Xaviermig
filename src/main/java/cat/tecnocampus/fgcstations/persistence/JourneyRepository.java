package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.JourneyDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFavoriteJourney;
import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.JourneyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface JourneyRepository extends JpaRepository<Journey, JourneyId> {

    @Query ("SELECT j FROM Journey j")
    List <Journey> getAllDomainJourney ();

    @Query ("SELECT j FROM Journey j")
    List <JourneyDTO > getAllJourneyDto ();

    @Query("SELECT j FROM Journey j WHERE j.origin.name = :originName AND j.destination.name = :destinationName")
    Optional <Journey> findByOriginAndDestination (@Param("origin")String origin, @Param("destination")String destination);



}
