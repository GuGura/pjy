package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import youtubep.pjy.domain.Comment;
import youtubep.pjy.domain.LikeComment;
import youtubep.pjy.domain.Video;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM Video_Comment WHERE video_UID = #{video_UID} order by Comment_UID desc")
    List<Comment> findAll(int video_UID);

    @Select("SELECT Icon_URL FROM Users where userID = #{userID}")
    String findIcon_URL(String userID);

    @Delete("DELETE Video_Comment where comment_UID = #{comment_UID}")
    void deleteComment(int comment_UID);

    @Insert("insert into Video_Comment (Comment_UID, Video_UID, Comment_UserID, Comment_Description) values (Comment_UID_seq.NEXTVAL, #{video_UID}, #{comment_UserID}, #{comment_Description})")
    void save(Comment comment);

    @Select("SELECT * FROM likeComment WHERE Video_UID = #{video_UID}")
    List<LikeComment> findlikeCommentss(int video_UID);

    @Select("SELECT count(*) FROM likeComment WHERE Comment_UID = #{Comment_UID}")
    int findLikeCount(int Comment_UID);

    @Insert("INSERT INTO likeComment (Comment_UID,Video_UID,UserID) values(#{comment_UID},#{video_UID},#{userID})")
    void saveCommentLike(LikeComment likeComment);

    @Delete("Delete from likeComment where Comment_UID = #{comment_UID} and Video_UID = #{video_UID} and UserID = #{userID}")
    void deleteCommetLike(LikeComment likeComment);
}
