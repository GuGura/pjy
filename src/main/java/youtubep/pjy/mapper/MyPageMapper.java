package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import youtubep.pjy.domain.User;
import youtubep.pjy.domain.Video;

import java.util.List;

@Mapper
public interface MyPageMapper {

    @Select("SELECT * FROM Users WHERE userID = #{userID}")
    User findMyInfo(String userID);

    @Select("SELECT COUNT(*) FROM Users where userID = #{userID} and password = #{password}")
    int checkPassword(User user);

    @Update("Update Users set Icon_URL = #{icon_URL},Banner_URL = #{banner_URL},channel_name = #{channel_name}, location = #{location}, description = #{description} where userID = #{userID}" )
    void update(User user);

    @Select("SELECT Icon_URL,Banner_URL FROM Users WHERE userID= #{userID}")
    User findURL(User user);

    @Select("SELECT * FROM video where Upload_User = #{userID} ORDER BY Video_Join_Date desc")
    List<Video> findMyVideos(String userID);

    @Select("SELECT * FROM VIDEO WHERE video_UID = #{video_UID}")
    Video findVideo(int video_UID);

    @Select("SELECT u.Icon_URL FROM video v JOIN users u ON v.Upload_User = u.userID WHERE v.Video_UID = #{video_UID}")
    String findUpload_userIcon(int video_UID);

    @Select("SELECT u.CHANNEL_NAME FROM video v JOIN users u ON v.Upload_User = u.userID WHERE v.Video_UID = #{video_UID}")
    String findUpload_channelName(int video_UID);
}
