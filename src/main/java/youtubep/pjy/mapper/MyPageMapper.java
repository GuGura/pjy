package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import youtubep.pjy.controller.mypage.MyPageInfo;
import youtubep.pjy.domain.User;

import java.util.Optional;

@Mapper
public interface MyPageMapper {

    @Insert("INSERT INTO channel_info (userID, Channel_id, Chanel_Name) VALUES (#{userID}, channel_id_seq.NEXTVAL, #{userID}||'님 어서오세요')")
    void saveID(String userID);

    @Select("SELECT * FROM channel_info where userID = #{userID}")
    Optional<User> findUser(String userID);

    @Select("SELECT * FROM channel_info WHERE userID = #{userID}")
    MyPageInfo findInfo(String userID);

}
