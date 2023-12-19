package sustech.cs309.sustechcampus.model;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Reservation {

  @Id
  @GeneratedValue
  private UUID rid;
  private UUID uid;
  private String reservationItem;
  private Date reservationTime;

  public Reservation() {
  }

  public Reservation(UUID uid, String reservationItem, Date reservationTime) {
    this.uid = uid;
    this.reservationItem = reservationItem;
    this.reservationTime = reservationTime;
  }

  public UUID getRid() {
    return rid;
  }

  public UUID getUid() {
    return uid;
  }

  public void setUid(UUID uid) {
    this.uid = uid;
  }

  public String getReservationItem() {
    return reservationItem;
  }

  public void setReservationItem(String reservationItem) {
    this.reservationItem = reservationItem;
  }

  public Date getReservationTime() {
    return reservationTime;
  }

  public void setReservationTime(Date reservationTime) {
    this.reservationTime = reservationTime;
  }
}
