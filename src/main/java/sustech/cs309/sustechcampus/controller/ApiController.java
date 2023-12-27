package sustech.cs309.sustechcampus.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
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
  public boolean postApiAccountVerify(HttpServletResponse response,
    @RequestParam(value = "username") String username,
    @RequestParam(value = "password") String password) {
    UUID uid = this.accountService.verifyAccount(username, password);
    if (uid == null) {
      return false;
    } else {
      Cookie cookie = new Cookie("uid", uid.toString());
      cookie.setPath("/");
      response.addCookie(cookie);
      return true;
    }
  }

  @PostMapping(value = "/account/register")
  public boolean postApiAccountRegister(HttpServletResponse response,
    @RequestParam(value = "username") String username,
    @RequestParam(value = "password") String password) {
    Account account = new Account(username, password);
    if (this.accountService.addAccount(account)) {
      Cookie cookie = new Cookie("uid", account.getUid().toString());
      cookie.setDomain("/");
      response.addCookie(cookie);
      return true;
    } else {
      return false;
    }
  }

  /*Building Apis*/
  @GetMapping(value = "/building/introduction", produces = "text/plain;charset=UTF-8")
  public String getApiBuildingIntroduction(@RequestParam(value = "name") String buildingName) {
    return this.buildingService.getBuildingByName(buildingName).get().getBuildingIntroduction();
  }

  @GetMapping(value = "/building/comment")
  public List<Comment> getApiBuildingComment(@RequestParam(value = "name") String buildingName) {
    Building building = this.buildingService.getBuildingByName(buildingName).get();
    List<Comment> commentList = new ArrayList<>();
    UUID cid = building.getFirstComment();
    while (true) {
      Comment comment = this.commentService.getCommentById(cid).get();
      commentList.add(comment);
      if (comment.getNextComment() == null) {
        break;
      }
      cid = comment.getNextComment();
    }
    return commentList;
  }

  @PostMapping(value = "/admin/building/edit")
  public boolean postApiBuildingEdit(@RequestParam(value = "oldName") String oldName,
    @RequestParam(value = "name") String name,
    @RequestParam(value = "introduction") String introduction,
    @RequestParam(value = "group") String group) {
    this.buildingService.editBuildingByName(oldName, name, introduction, group);
    return true;
  }

  /*Comment Apis*/
  @PostMapping(value = {"/comment/add"})
  public void postApiCommentAdd(@CookieValue(value = "uid") String uid,
    @RequestParam(value = "buildingName") String buildingName,
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

  @GetMapping(value = {"/comment/list"})
  public Comment postApiCommentList(@RequestParam(value = "cid") String cid) {
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

  @GetMapping(value = {"/reservation/list"})
  public List<Reservation> postApiReservationList() {
    return this.reservationService.listAllReservation();
  }

  @PostMapping(value = {"/reservation/reserve"})
  public boolean postApiReservationList(@CookieValue(value = "uid") String uid,
    @RequestParam(value = "rid") String rid) {
    return this.reservationService.reservationByUid(UUID.fromString(rid), UUID.fromString(uid));
  }
}
