package youtubep.pjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import youtubep.pjy.domain.User;
import youtubep.pjy.service.MemberService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private MemberService memberService;

    @Autowired
    public UserController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 로그인
     * @param model 로그인 실패 시 login,form.getId() 전달
     * @param session  로그인 성공 시 login,form.getId() 전달
     */
    @PostMapping("/login")
    public String loginCheck(LoginForm form, HttpSession session, Model model){
        User user = new User();
        user.setUserID(form.getUserID());
        user.setPassword(form.getPassword());
        int result = memberService.login(user);
        if(result == 1){
            // 로그인 성공 시 세션에 정보를 담아서 리다이렉트
            session.setAttribute("login", true);
            session.setAttribute("userID",form.getUserID());
            System.out.println("321");
            return "main_public";
        }else{
            // 로그인 실패 시 모델에 에러 메시지를 담아서 포워드
            model.addAttribute("login","false");
            model.addAttribute("userID", form.getUserID());
            return "login";
        }
    }

    @GetMapping("/singUp")
    public String singUp(){
        return "singup";
    }

    /**
     * 회원가입
     */
    @PostMapping("/singUp")
    public String singUpCheck(SingUpForm form, Model model){
        User user = new User();
        user.setUserID(form.getUserID());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());
        memberService.join(user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


    /**
     * 중복아이디 체크
     * @return 중복아이디 없으면 넘어온 파라미러 model에 담아서 보내줌
     */
    @PostMapping("/checkId")
    public String checkId(SingUpForm form,Model model){
        boolean result = memberService.validateDuplicateMember(form.getUserID());
        if(result == true){
            model.addAttribute("isCheckId","true");
        }else if(result == false){
            model.addAttribute("isCheckId","false");
            model.addAttribute("userID",form.getUserID());
            model.addAttribute("passwd",form.getPassword());
            model.addAttribute("email",form.getEmail());
        }
        return "singup";
    }

}