package youtubep.pjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import youtubep.pjy.domain.Video;
import youtubep.pjy.service.MainPublicService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainPublic {

    private final MainPublicService mainPublicService;

    @Autowired
    public MainPublic(MainPublicService mainPublicService) {
        this.mainPublicService = mainPublicService;
    }


    @GetMapping("/")
    public String Home(HttpSession session){
        List<Video> videos= mainPublicService.findAll();
        session.setAttribute("videos",videos);
        return "main_public";
    }
    @GetMapping("/bbs")
    public String bbs(){
        return "bbs";
    }
}
