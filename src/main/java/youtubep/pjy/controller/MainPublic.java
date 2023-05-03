package youtubep.pjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import youtubep.pjy.domain.Video;
import youtubep.pjy.service.MainPublicService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPublic {

    private final MainPublicService mainPublicService;

    @Autowired
    public MainPublic(MainPublicService mainPublicService) {
        this.mainPublicService = mainPublicService;
    }


    @GetMapping("/")
    public String home(HttpSession session){
        String category = "resent";
        List<Video> videos= mainPublicService.findAll(category);
        session.setAttribute("videos",videos);
        return "main_public";
    }
    @GetMapping("/bbs")
    public String bbs(){
        return "bbs";
    }

    @PostMapping("/listview")
    public String homeView(@RequestParam("category") String category, HttpSession session){
        List<Video> videos= mainPublicService.findAll(category);
        session.setAttribute("videos",videos);
        return "listView";
    }

    @PostMapping("/searchPage")
    public String searchPage(@RequestParam("search") String search, HttpSession session){
        int isUserExist = mainPublicService.isUploaderExist(search);
        List<Video> searchUploader = new ArrayList<>();
        List<Video> searchVideoName = new ArrayList<>();
        System.out.println("isUserExist: "+isUserExist);
        if (isUserExist >= 1){
            searchUploader = mainPublicService.findUploaderVideo(search);
            searchVideoName = mainPublicService.findSearchVideo(search,searchUploader);
        }
        else{
            searchVideoName = mainPublicService.findSearchVideo(search,searchUploader);
            searchUploader = null;
        }
        session.setAttribute("search",search);
        session.setAttribute("searchUploader",searchUploader);
        session.setAttribute("videos",searchVideoName);
        return "SearchContent";
    }
}
