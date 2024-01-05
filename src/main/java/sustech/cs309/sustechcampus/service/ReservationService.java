package sustech.cs309.sustechcampus.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.model.Reservation;
import sustech.cs309.sustechcampus.repository.ReservationRepository;

@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;

  @Autowired
  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
    initReservation();
  }

  public void addReservation(Reservation reservation) {
    this.reservationRepository.save(reservation);
  }

  public List<Reservation> listAllReservation() {
    return this.reservationRepository.findAll();
  }

  public boolean reservationByUid(UUID rid, UUID uid) {
    Optional<Reservation> reservation = this.reservationRepository.findById(rid);
    if (reservation.isEmpty()) {
      return false;
    } else if (reservation.get().getUid() != null) {
      return false;
    }
    reservation.get().setUid(uid);
    this.reservationRepository.save(reservation.get());
    return true;
  }

  public void initReservation() {
    List<Reservation> reservationList = new ArrayList<>();
    reservationList.add(new Reservation("猪脚饭", new Date(124, 0, 1), new Date(124, 0, 1),"中心食堂"));
    reservationList.add(new Reservation("鸡脚饭", new Date(124, 0, 1), new Date(124, 0, 1),"中心食堂"));
    reservationList.add(new Reservation("牛脚饭", new Date(124, 0, 1), new Date(124, 0, 1),"中心食堂"));
    reservationList.add(new Reservation("猪脚饭", new Date(124, 0, 1), new Date(124, 0, 1),"第二学生食堂"));
    reservationList.add(new Reservation("鸡脚饭", new Date(124, 0, 1), new Date(124, 0, 1),"第二学生食堂"));
    reservationList.add(new Reservation("牛脚饭", new Date(124, 0, 1), new Date(124, 0, 1),"第二学生食堂"));
    this.reservationRepository.saveAll(reservationList);
  }
}
