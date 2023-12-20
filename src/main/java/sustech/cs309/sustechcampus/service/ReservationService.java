package sustech.cs309.sustechcampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.model.Comment;
import sustech.cs309.sustechcampus.model.Reservation;
import sustech.cs309.sustechcampus.repository.ReservationRepository;

@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;

  @Autowired
  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  public void addReservation(Reservation reservation) {
    this.reservationRepository.save(reservation);
  }
}
