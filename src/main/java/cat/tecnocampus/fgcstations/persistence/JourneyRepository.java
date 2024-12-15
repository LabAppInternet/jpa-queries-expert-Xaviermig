package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.JourneyDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFavoriteJourney;
import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.JourneyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface JourneyRepository extends JpaRepository<Journey, JourneyId> {

    @Query ("SELECT j FROM Journey j")
    List <Journey> getAllDomainJourney ();

    @Query ("SELECT j FROM Journey j")
    List <JourneyDTO > getAllJourneyDto ();

    Optional <Journey> findJourneyBy (String origin,String destination);
    Optional <JourneyId> findJourneyId (String origin, String destination);



}
