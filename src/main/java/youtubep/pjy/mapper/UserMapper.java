package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import youtubep.pjy.domain.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO Users (user_UID,userID,password,email,CHANNEL_NAME) values (user_UID_seq.NEXTVAL,#{userID},#{password},#{email}, #{userID} ||'님 환영합니다')")
    void save(User user);

    @Select("SELECT COUNT(*) FROM Users WHERE userID = #{userID} and password = #{password}")
    Optional<Integer> findUser(User user);

    @Select("SELECT * FROM Users WHERE userID = #{userID}")
    Optional<User> findById(String id);

    @Select("SELECT * FROM Users WHERE email = #{email}")
    Optional<User> findByEmail(String email);

    @Select("SELECT * FROM Users")
    List<User> findAll();
}
