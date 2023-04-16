package youtubep.pjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youtubep.pjy.controller.mypage.MyPageInfo;
import youtubep.pjy.mapper.MyPageMapper;

@Service
public class MyPageService {

    private final MyPageMapper myPageMapper;

    @Autowired
    public MyPageService(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }

    public MyPageInfo goMyPage(String userID) {
        boolean result = myPageMapper.findUser(userID).isPresent();
        if(result == false){
            myPageMapper.saveID(userID);
        }
        return myPageMapper.findInfo(userID);
    }

}
