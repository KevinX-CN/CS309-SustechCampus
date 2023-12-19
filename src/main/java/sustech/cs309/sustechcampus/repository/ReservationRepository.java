package sustech.cs309.sustechcampus.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sustech.cs309.sustechcampus.model.Comment;
import sustech.cs309.sustechcampus.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

}