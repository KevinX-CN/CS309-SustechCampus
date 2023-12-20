package sustech.cs309.sustechcampus.controller;

import java.util.Date;
import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sustech.cs309.sustechcampus.model.Account;
import sustech.cs309.sustechcampus.model.Building;
import sustech.cs309.sustechcampus.model.Comment;
import sustech.cs309.sustechcampus.model.Reservation;
import sustech.cs309.sustechcampus.service.AccountService;
import sustech.cs309.sustechcampus.service.BuildingService;
import sustech.cs309.sustechcampus.service.CommentService;
import sustech.cs309.sustechcampus.service.ReservationService;

@RestController
@CrossOrigin
public class ApiController {

  private final AccountService accountService;
  private final BuildingService buildingService;
  private final CommentService commentService;
  private final ReservationService reservationService;

  public ApiController(AccountService accountService, BuildingService buildingService,
    CommentService commentService, ReservationService reservationService) {
    this.accountService = accountService;
    this.buildingService = buildingService;
    this.commentService = commentService;
    this.reservationService = reservationService;
  }

  /*Account Apis*/
  @PostMapping(value = {"/api/account/login", "/login"})
  public boolean postApiAccountVerify(@RequestParam(value = "username") String username,
    @RequestParam(value = "password") String password) {
    return this.accountService.verifyAccount(username, password);
  }

  @PostMapping(value = {"/api/account/register", "/register"})
  public void postApiAccountRegister(@RequestParam(value = "username") String username,
    @RequestParam(value = "password") String password) {
    Account account = new Account(username, password);
    this.accountService.addAccount(account);
  }

  /*Building Apis*/
  @GetMapping(value = "/api/building")
  public String getApiBuilding(@RequestParam(value = "name") String buildingName) {
    return this.buildingService.getBuildingByName(buildingName).toString();
  }

  /*Comment Apis*/
  @PostMapping(value = {"/api/comment/add"})
  public void postApiCommentAdd(@RequestParam(value = "buildingName") String buildingName,
    @RequestParam(value = "uid") UUID uid, @RequestParam(value = "content") String commentContent) {
    Building building = this.buildingService.getBuildingByName(buildingName).get();
    Comment comment = new Comment(uid, building.getBid(), commentContent, new Date());
    this.commentService.addComment(comment);
    this.buildingService.addCommentToBuilding(building, comment);
  }

  /*Reservation Apis*/
  @PostMapping(value = {"/api/reservation/add"})
  public void postApiReservationAdd(@RequestParam(value = "item") String reservationItem,
    @RequestParam(value = "time") Date reservationTime) {
    Reservation reservation = new Reservation(reservationItem, reservationTime);
    this.reservationService.addReservation(reservation);
  }
}
