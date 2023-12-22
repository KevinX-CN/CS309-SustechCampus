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
  private Date reservationStartTime;
  private Date reservationEndTime;

  public Reservation() {
  }

  public Reservation(String reservationItem, Date reservationStartTime,Date reservationEndTime) {
    this.uid = null;
    this.reservationItem = reservationItem;
    this.reservationStartTime = reservationStartTime;
    this.reservationEndTime=reservationEndTime;
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

  public Date getReservationStartTime() {
    return reservationStartTime;
  }

  public void setReservationStartTime(Date reservationStartTime) {
    this.reservationStartTime = reservationStartTime;
  }

  public Date getReservationEndTime() {
    return reservationEndTime;
  }

  public void setReservationEndTime(Date reservationEndTime) {
    this.reservationEndTime = reservationEndTime;
  }
}
