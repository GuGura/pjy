package youtubep.pjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youtubep.pjy.domain.User;
import youtubep.pjy.mapper.UserMapper;

import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final UserMapper userMapper;

    @Autowired
    public MemberService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 로그인
     * @param user
     * @return 회원이 존재하면 1, 없으면 0
     */
    public int login(User user){
        return userMapper.findUser(user).orElse(0);
    }

    /**
     * 회원가입
     * @param user
     */
    public void join(User user) {
        userMapper.save(user);
    }

    /**
     * 중복체크
     * @param id
     * @return boolean, 이미 아이디가 존재하면 true, 아이디가 존재하지않으면 false
     */
    public boolean validateDuplicateMember(String id) {
        Optional<User> result = userMapper.findById(id);
        return result.isPresent();
    }
}
