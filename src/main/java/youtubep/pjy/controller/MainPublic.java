package youtubep.pjy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPublic {

    @GetMapping("/")
    public String Home(){
        return "main_public";
    }
    @GetMapping("/bbs")
    public String bbs(){
        return "bbs";
    }
}
