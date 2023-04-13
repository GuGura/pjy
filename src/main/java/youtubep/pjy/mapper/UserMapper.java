package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import youtubep.pjy.domain.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO Users (userID,password,email) values (#{userID},#{password},#{email})")
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
