package sustech.cs309.sustechcampus.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api")
@CrossOrigin
@Tag(name = "Apis")
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
  @PostMapping(value = "/account/login")
  public boolean postApiAccountVerify(@RequestParam(value = "username") String username,
    @RequestParam(value = "password") String password) {
    return this.accountService.verifyAccount(username, password);
  }

  @PostMapping(value = "/account/register")
  public boolean postApiAccountRegister(@RequestParam(value = "username") String username,
    @RequestParam(value = "password") String password) {
    Account account = new Account(username, password);
    this.accountService.addAccount(account);
    return true;
  }

  /*Building Apis*/
  @GetMapping(value = "/building/introduction")
  public String getApiBuilding(@RequestParam(value = "name") String buildingName) {
    return this.buildingService.getBuildingByName(buildingName).get().getBuildingIntroduction();
  }

  @GetMapping(value = "/building/comment")
  public List<UUID> getApiBuildingComment(@RequestParam(value = "name") String buildingName) {
    Building building = this.buildingService.getBuildingByName(buildingName).get();
    List<UUID> commentList = new ArrayList<>();
    UUID cid = building.getFirstComment();
    while (true) {
      Comment comment = this.commentService.getCommentById(cid).get();
      commentList.add(comment.getCid());
      if (comment.getNextComment() == null) {
        break;
      }
      cid = comment.getNextComment();
    }
    return commentList;
  }

  /*Comment Apis*/
  @PostMapping(value = {"/comment/add"})
  public void postApiCommentAdd(@RequestParam(value = "buildingName") String buildingName,
    @RequestParam(value = "uid") String uid,
    @RequestParam(value = "content") String commentContent) {
    Building building = this.buildingService.getBuildingByName(buildingName).get();
    Comment comment = new Comment(UUID.fromString(uid), building.getBid(), commentContent,
      new Date());
    this.commentService.addComment(comment);
    if (!(building.getLastComment() == null)) {
      this.commentService.setNextComment(building.getLastComment(), comment.getCid());
    }
    this.buildingService.addCommentToBuilding(building, comment);
  }

  @GetMapping(value = {"/comment/view"})
  public Comment postApiComment(@RequestParam(value = "cid") String cid) {
    Comment comment = this.commentService.getCommentById(UUID.fromString(cid)).get();
    return comment;
  }

  /*Reservation Apis*/
  @PostMapping(value = {"/reservation/add"})
  public void postApiReservationAdd(@RequestParam(value = "item") String reservationItem,
    @RequestParam(value = "time") Date reservationStartTime,
    @RequestParam(value = "time") Date reservationEndTime) {
    Reservation reservation = new Reservation(reservationItem, reservationStartTime,
      reservationEndTime);
    this.reservationService.addReservation(reservation);
  }
}
