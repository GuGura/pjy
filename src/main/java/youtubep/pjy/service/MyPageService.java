package youtubep.pjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youtubep.pjy.domain.User;
import youtubep.pjy.mapper.MyPageMapper;

@Service
public class MyPageService {

    private final MyPageMapper myPageMapper;

    @Autowired
    public MyPageService(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }

    public User goMyPage(String userID) {
        return myPageMapper.findMyInfo(userID);
    }

    public String checkPassword(User user) {
        int result =  myPageMapper.checkPassword(user);
        if(result == 1)
            return "bbs";
        else
            return "checkPassword";
    }

    public void update(User user) {
        myPageMapper.update(user);
    }
}
