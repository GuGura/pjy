package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import youtubep.pjy.domain.Comment;
import youtubep.pjy.domain.Video;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM Video_Comment WHERE video_UID = #{video_UID} order by Comment_UID desc")
    List<Comment> findAll(int video_UID);

    @Select("SELECT Icon_URL FROM Users where userID = #{userID}")
    String findIcon_URL(String userID);

    @Delete("DELETE Video_Comment where video_UID = #{video_UID}")
    void deleteComment(int video_UID);

    @Insert("insert into Video_Comment (Comment_UID, Video_UID, Comment_UserID, Comment_Description) values (Comment_UID_seq.NEXTVAL, #{video_UID}, #{comment_UserID}, #{comment_Description})")
    void save(Comment comment);
}
