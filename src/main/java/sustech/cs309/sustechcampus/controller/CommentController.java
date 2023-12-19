package sustech.cs309.sustechcampus.controller;

import java.util.Date;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sustech.cs309.sustechcampus.model.Building;
import sustech.cs309.sustechcampus.model.Comment;
import sustech.cs309.sustechcampus.service.BuildingService;
import sustech.cs309.sustechcampus.service.CommentService;

public class CommentController {

  private final BuildingService buildingService;
  private final CommentService commentService;

  public CommentController(BuildingService buildingService, CommentService commentService) {
    this.buildingService = buildingService;
    this.commentService = commentService;
  }

  @PostMapping(value = {"/api/comment/add"})
  public void apiCommentAdd(@RequestParam(value = "buildingName") String buildingName,
    @RequestParam(value = "uid") UUID uid, @RequestParam(value = "content") String commentContent) {
    Building building = this.buildingService.getBuildingByName(buildingName).get();
    Comment comment = new Comment(uid, building.getBid(), commentContent, new Date());
    this.commentService.addComment(comment);
    this.buildingService.addCommentToBuilding(building, comment);
  }


}
