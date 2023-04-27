package youtubep.pjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import youtubep.pjy.domain.Comment;
import youtubep.pjy.domain.User;
import youtubep.pjy.domain.UserForm;
import youtubep.pjy.domain.Video;
import youtubep.pjy.service.CommentService;
import youtubep.pjy.service.MyPageService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static youtubep.pjy.controller.FileUploadController.uploadDirectory;

@Controller
public class MyPageController {

    private final MyPageService myPageService;
    private final CommentService commentService;

    @Autowired
    public MyPageController(MyPageService myPageService, CommentService commentService) {
        this.myPageService = myPageService;
        this.commentService = commentService;
    }

    @GetMapping("/MyPage")
    public String MyPageInfo(HttpSession session){
        String userID = (String)session.getAttribute("userID");
        User user = myPageService.goMyPage(userID);
        List<Video> videos = myPageService.myVideos(userID);

        session.setAttribute("videos",videos);
        session.setAttribute("MyInfo",user);
        return "/bbs";
    }

    @GetMapping("/bbsUpdate")
    public String UpdateMyPageInfo(){
        return "/checkPassword";
    }

    @PostMapping("/bbsUpdate")
    public String checkPassword(UserForm form, HttpSession session, Model model){
        String userID = (String)session.getAttribute("userID");
        User user = new User();
        user.setUserID(userID);
        user.setPassword(form.getPassword());
        String url = myPageService.checkPassword(user);
        if(url.equals("bbs")){
            model.addAttribute("checkPasswd",true);
            System.out.println(model.getAttribute("checkPasswd"));
        }
        return url;
    }
    @PostMapping("/update")
    public String updateMyInfo(UserForm form,
                               HttpSession session,
                               @RequestParam(value = "icon", required = false) MultipartFile icon,
                               @RequestParam(value = "banner", required = false) MultipartFile banner){
        String userID = (String)session.getAttribute("userID");
        System.out.println(banner.getSize());
        System.out.println(icon.getSize());
        String path = uploadDirectory + userID+ "\\info";
        new File(path).mkdirs();
        User user = new User();
        user.setUserID(userID);
        user.setChannel_name(form.getChannel_name());
        user.setLocation(form.getLocation());
        user.setDescription(form.getDescription());
        List<String> url = myPageService.getURL(user);
        user.setIcon_URL(url.get(0));
        user.setBanner_URL(url.get(1));
        if (icon.getSize() != 0) {
            Path iconName = Paths.get(path, icon.getOriginalFilename());
            user.setIcon_URL(iconName.toString());
            try {
                Files.write(iconName, icon.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (banner.getSize() != 0) {
            Path bannerName = Paths.get(path, banner.getOriginalFilename());
            user.setBanner_URL(bannerName.toString());
            try {
                Files.write(bannerName, banner.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        myPageService.update(user);

        return "redirect:/MyPage";
    }
    @GetMapping("/videoPage/{video_UID}")
    public String VideoPage(@PathVariable int video_UID, HttpSession session){

        Video thisVideo = myPageService.findVideo(video_UID);
        List<Comment> comments = commentService.findAll(video_UID);
        session.setAttribute("comments",comments);
        session.setAttribute("thisVideo",thisVideo);
        return "/videoPage";
    }
    @GetMapping("/videoPage")
    public String VideoPage(){
        return "videoPage";
    }
    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {
    }
}
