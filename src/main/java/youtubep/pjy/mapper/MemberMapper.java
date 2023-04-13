package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import youtubep.pjy.domain.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO Users (id,password,email) values (#{id},#{password},#{email})")
    void save(User user);

    @Select("SELECT COUNT(*) FROM Users WHERE id = #{id} and password = #{password}")
    Optional<Integer> findUser(User user);

    @Select("SELECT * FROM Users WHERE id = #{id}")
    Optional<User> findById(String id);

    @Select("SELECT * FROM Users WHERE email = #{email}")
    Optional<User> findByEmail(String email);

    @Select("SELECT * FROM Users")
    List<User> findAll();
}
