package youtubep.pjy.controller.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        System.out.println(userID);
        MyPageInfo myPageInfo = myPageService.goMyPage(userID);
        session.setAttribute("MyInfo",myPageInfo);
        return "/bbs";
    }

}
