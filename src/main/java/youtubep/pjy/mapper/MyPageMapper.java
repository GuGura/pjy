package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.*;
import youtubep.pjy.domain.LikeVideo;
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

    @Update("Update VIDEO set Views =  (Select Views+1 from VIDEO where Video_UID = #{video_UID} ) where VIDEO_UID = #{video_UID}")
    void updateViews(int video_UID);

    @Select("SELECT u.Icon_URL FROM video v JOIN users u ON v.Upload_User = u.userID WHERE v.Video_UID = #{video_UID}")
    String findUpload_userIcon(int video_UID);

    @Select("SELECT u.CHANNEL_NAME FROM video v JOIN users u ON v.Upload_User = u.userID WHERE v.Video_UID = #{video_UID}")
    String findUpload_channelName(int video_UID);

    @Select("SELECT count(*) FROM VideoLike WHERE Video_UID = #{video_UID}")
    int VideoLikeCtn(int video_UID);

    @Insert("INSERT INTO VideoLike (Video_UID,userID) VALUES(#{video_UID},#{userID})")
    void saveVideoLikeCtn(LikeVideo likeVideo);

    @Delete("delete from VideoLike where Video_UID = #{video_UID} and userID = #{userID}")
    void deleteVideoLikeCtn(LikeVideo likeVideo);

    @Select("SELECT b.userID\n" +
            "from video a\n" +
            "LEFT JOIN (\n" +
            "SELECT * FROM VIDEOLIKE WHERE Video_UID = #{video_UID} AND userID = #{userID})\n" +
            "b ON a.VIDEO_UID = b.Video_UID\n" +
            "WHERE a.VIDEO_UID = #{video_UID}")
    String findisChecked(int video_UID,String userID);

    @Insert("update video set Video_Like=#{VideoLikeCtn} where Video_UID = #{video_UID}")
    void insertVideoLikeCtn(int video_UID,int VideoLikeCtn);
}
