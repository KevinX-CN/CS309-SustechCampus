package sustech.cs309.sustechcampus.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.crypto.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.model.Building;
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

  public void initReservation() {
    List<Reservation> reservationList = new ArrayList<>();
    reservationList.add(new Reservation("猪脚饭", new Date(1704081600), new Date(1704088800)));
    reservationList.add(new Reservation("鸡脚饭", new Date(1704081600), new Date(1704088800)));
    reservationList.add(new Reservation("牛脚饭", new Date(1704081600), new Date(1704088800)));
    this.reservationRepository.saveAll(reservationList);
  }
}
