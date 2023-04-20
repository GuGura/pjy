package youtubep.pjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import youtubep.pjy.domain.User;
import youtubep.pjy.domain.UserForm;
import youtubep.pjy.service.MyPageService;

import javax.servlet.http.HttpSession;
@Controller
public class MyPageController {

    private MyPageService myPageService;

    @Autowired
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/MyPage")
    public String MyPageInfo(HttpSession session){
        String userID = (String)session.getAttribute("userID");
        User user = myPageService.goMyPage(userID);
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
    public String updateMyInfo(UserForm form, HttpSession session){
        String userID = (String)session.getAttribute("userID");
        User user = new User();
        user.setUserID(userID);
        user.setChannel_name(form.getChannel_name());
        user.setLocation(form.getLocation());
        user.setDescription(form.getDescription());
        myPageService.update(user);

        return "redirect:/MyPage";
    }
}
