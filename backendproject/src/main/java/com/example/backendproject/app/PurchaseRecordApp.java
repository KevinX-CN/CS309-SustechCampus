package com.example.backendproject.app;

import com.example.backendproject.domain.*;
import com.example.backendproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/exer")
public class PurchaseRecordApp {
    private PurchaseRecordService purchaseRecordService;
    private UserService userService;
    private ShopService shopService;

    private CommentService commentService;

    private RoomService roomService;

    private ReserveService reserveService;

    private BuildingService buildingService;
    @Autowired
    public PurchaseRecordApp(PurchaseRecordService purchaseRecordService, UserService userService, ShopService shopService, CommentService commentService, RoomService roomService, ReserveService reserveService, BuildingService buildingService) {
        this.purchaseRecordService = purchaseRecordService;
        this.userService = userService;
        this.shopService = shopService;
        this.commentService = commentService;
        this.roomService = roomService;
        this.reserveService = reserveService;
        this.buildingService = buildingService;
    }

    @GetMapping("/record")
    public List<PurchaseRecord> findAll(){
        System.out.println(purchaseRecordService.getClass().getName());
        return purchaseRecordService.findAll();
    }
    @PostMapping("/record")
    public PurchaseRecord addOne(PurchaseRecord purchaseRecord){
        return purchaseRecordService.save(purchaseRecord);
    }
    @GetMapping("/shop")
    public List<ShopList> findAShopAll(){
        return shopService.findAll();
    }

    @GetMapping("/login/{name}/{password}")
    public Users valid(@PathVariable("name") String name,@PathVariable("password") String password){
        Users users =userService.findByName(name);
        Users users1 =new Users(name,password);
        users1.setStatus("notexit");
        if (users!=null){
            // get the current date and time
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            users.setLastDate(dateTime.format(formatter));
            userService.save(users);
            return users;
        }
        return users1;
    }

    @PostMapping("/register")
    public String regist(@RequestParam("name") String name, @RequestParam("password") String password){
        Users users =new Users(name,password);
        userService.save(users);
        return "yes";
    }

    @PostMapping("/newcomment")
    public String newcomment(@RequestBody Map<String, String> person){
        Comment tem =new Comment(person.get("username"),person.get("comment"),person.get("image"));
        commentService.save(tem);
        return "yes";
    }

    @PostMapping("/changecomment")
    public String changecomment(@RequestParam("username") String username, @RequestParam("comment") String comment){
        Comment tem =commentService.findByUsernameaAndComment(username,comment);
        tem.setIsvist("yes");
        commentService.save(tem);
        return "yes";
    }
    @GetMapping("/commentt")
    public List<Comment> findcommentt(){
        return commentService.findBytrue();
    }

    @GetMapping("/commentf")
    public List<Comment> findcommentf(){
        return commentService.findByfalse();
    }

    @GetMapping("/room/{building}/{floor}")
    public List<Room> findroom(@PathVariable("building") String building,@PathVariable("floor") String floor){
        return roomService.findByBuildingAndFloor(building, floor);
    }

    @GetMapping("/reserve/{username}/{roomname}/{data}/{time}")
    public String findreserve(@PathVariable("username") String username,@PathVariable("roomname") String roomname,@PathVariable("data") String data,@PathVariable("time") String time){
        if (reserveService.findByUsernameAndRoomnameAndDataAndTime(username, roomname, data, time)!=null){
            return "no";
        }
        Reserve tem =new Reserve(username, roomname, data, time);
        reserveService.save(tem);
        return "yes";
    }

    @GetMapping("/findreserve/{username}")
    public List<Reserve> findallreserve(@PathVariable("username") String username){
        return reserveService.findByUsername(username);
    }

    @GetMapping("/deletereserve/{username}/{roomname}/{data}/{time}")
    public String deletereserve(@PathVariable("username") String username,@PathVariable("roomname") String roomname,@PathVariable("data") String data,@PathVariable("time") String time){
        Reserve tem =reserveService.findByUsernameAndRoomnameAndDataAndTime(username, roomname, data, time);
        reserveService.delete(tem);
        return "yes";
    }

    @GetMapping("/finduser")
    public List<Users> finduser(){
        return userService.findByType("user");
    }

    @GetMapping("/changeuser/{name}")
    public String findusername(@PathVariable("name") String name){
        Users tem =userService.findByName(name);
        if (Objects.equals(tem.getStatus(), "white")){
            tem.setStatus("black");
        }else {
            tem.setStatus("white");
        }
        userService.save(tem);
        return "yes";
    }

    @GetMapping("/zhuceuser")
    public String zhuceuser(){
        Random random =new Random();
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int j = 0; j < 10; j++) {
            StringBuilder sb =new StringBuilder();
            StringBuilder sbr =new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int number=random.nextInt(62);
            sbr.append(number);
            sb.append(str.charAt(number));
        }
            Users tem =new Users(sb.toString(),sbr.toString());
            userService.save(tem);
        }
        return "yes";
    }

    @GetMapping("/findbuilding/{name}")
    public Building findb(@PathVariable("name") String name){
        return buildingService.findByBname(name);
    }
}
