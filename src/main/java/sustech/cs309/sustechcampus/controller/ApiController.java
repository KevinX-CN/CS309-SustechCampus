package sustech.cs309.sustechcampus.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sustech.cs309.sustechcampus.model.Account;
import sustech.cs309.sustechcampus.model.Account.authorityType;
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

  @GetMapping(value = "/test/initComment")
  public boolean testInitComment() {
    Building building = this.buildingService.getBuildingByName("理学院").get();

    UUID uid1 = this.accountService.getUidByUsername("user1");
    UUID uid2 = this.accountService.getUidByUsername("user2");
    Comment comment1 = new Comment(uid1, building.getBid(), "哈哈",
      new Date(), "");
    this.commentService.addComment(comment1);
    if (!(building.getLastComment() == null)) {
      this.commentService.setNextComment(building.getLastComment(), comment1.getCid());
    }
    this.buildingService.addCommentToBuilding(building, comment1);
    Comment comment2 = new Comment(uid2, building.getBid(), "噜噜",
      new Date(), "");
    this.commentService.addComment(comment2);
    if (!(building.getLastComment() == null)) {
      this.commentService.setNextComment(building.getLastComment(), comment2.getCid());
    }
    this.buildingService.addCommentToBuilding(building, comment2);
    Comment comment3 = new Comment(uid2, building.getBid(), "嗷嗷",
      new Date(), "");
    this.commentService.addComment(comment3);
    if (!(building.getLastComment() == null)) {
      this.commentService.setNextComment(building.getLastComment(), comment3.getCid());
    }
    this.buildingService.addCommentToBuilding(building, comment3);
    Comment comment4 = new Comment(uid1, building.getBid(), "呼呼",
      new Date(), "");
    this.commentService.addComment(comment4);
    if (!(building.getLastComment() == null)) {
      this.commentService.setNextComment(building.getLastComment(), comment4.getCid());
    }
    this.buildingService.addCommentToBuilding(building, comment4);
    return true;
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
    Account account = new Account(username, password, authorityType.User);
    if (this.accountService.addAccount(account)) {
      Cookie cookie = new Cookie("uid", account.getUid().toString());
      cookie.setPath("/");
      response.addCookie(cookie);
      return true;
    } else {
      return false;
    }
  }

  /*Building Apis*/
  @GetMapping(value = "/building/introduction", produces = "text/plain;charset=UTF-8")
  public String getApiBuildingIntroduction(@RequestParam(value = "name") String buildingName) {
    return this.buildingService.getBuildingByName(buildingName).get().getBriefInfo();
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
    @RequestParam(value = "content") String commentContent,
    @Param("file") MultipartFile file) throws IOException {
    String path = "";
    if (file != null) {
      String filename = UUID.randomUUID().toString();
      path =
        "D:\\Projects\\CS309\\SustechCampus\\target\\classes\\static\\images" + File.separator
          + "upload" + File.separator
          + filename + ".png";

      File destination = new File(path);
      if (!destination.getParentFile().exists()) {
        FileUtils.forceMkdirParent(destination);
      }
      file.transferTo(destination);
      path = "/images/upload/" + filename + ".png";
    }
    Building building = this.buildingService.getBuildingByName(buildingName).get();
    Comment comment = new Comment(UUID.fromString(uid), building.getBid(), commentContent,
      new Date(), path);
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
    @RequestParam(value = "time") Date reservationEndTime,@RequestParam(value = "building") String buildingName) {
    Reservation reservation = new Reservation(reservationItem, reservationStartTime,
      reservationEndTime,buildingName);
    this.reservationService.addReservation(reservation);
  }

  @GetMapping(value = {"/reservation/list"})
  public List<Reservation> postApiReservationList(@CookieValue(value = "uid") String uid,
    @Param(value = "building") String building, @Param(value = "item") String item) {
    if (uid == null) {
      return null;
    }
    List<Reservation> reservationList = this.reservationService.listAllReservation().stream()
      .filter((r) -> {
        return r.getUid() != UUID.fromString(uid);
      }).toList();
    if (building != null) {
      reservationList = reservationList.stream().filter((r) -> {
        return !r.getBuildingName().equals(building);
      }).toList();
    }
    if (item != null) {
      reservationList = reservationList.stream().filter((r) -> {
        return !r.getReservationItem().equals(item);
      }).toList();
    }
    return reservationList;
  }

  @PostMapping(value = {"/reservation/reserve"})
  public boolean postApiReservationList(@CookieValue(value = "uid") String uid,
    @RequestParam(value = "rid") String rid) {
    return this.reservationService.reservationByUid(UUID.fromString(rid), UUID.fromString(uid));
  }
}
