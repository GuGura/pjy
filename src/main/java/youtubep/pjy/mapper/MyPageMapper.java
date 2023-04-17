package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import youtubep.pjy.domain.User;

@Mapper
public interface MyPageMapper {

    @Select("SELECT * FROM Users WHERE userID = #{userID}")
    User findMyInfo(String userID);

    @Select("SELECT COUNT(*) FROM Users where userID = #{userID} and password = #{password}")
    int checkPassword(User user);

    @Update("Update Users set channel_name = #{channel_name}, location = #{location}, description = #{description} where userID = #{userID}" )
    void update(User user);
}
