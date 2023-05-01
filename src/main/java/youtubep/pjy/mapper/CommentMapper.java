package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.*;
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

    @Select("SELECT b.USERID\n" +
            "FROM VIDEO_COMMENT a\n" +
            "LEFT JOIN (\n" +
            "    SELECT *\n" +
            "    FROM LIKECOMMENT\n" +
            "    WHERE USERID = #{userID} AND VIDEO_UID = #{video_UID}\n" +
            ") b ON a.COMMENT_UID = b.COMMENT_UID\n" +
            "WHERE a.VIDEO_UID = #{video_UID}\n" +
            "Order by a.COMMENT_UID desc")
    List<String> findlikeCommentss(int video_UID, String userID);

    @Select("SELECT count(*) FROM likeComment WHERE Comment_UID = #{Comment_UID}")
    int findLikeCount(int Comment_UID);

    @Insert("INSERT INTO likeComment (Comment_UID,Video_UID,UserID) values(#{comment_UID},#{video_UID},#{userID})")
    void saveCommentLike(LikeComment likeComment);

    @Delete("Delete from likeComment where Comment_UID = #{comment_UID} and Video_UID = #{video_UID} and UserID = #{userID}")
    void deleteCommetLike(LikeComment likeComment);

    @Update("update Video_Comment set Comment_Like = #{commentLike} where Comment_UID = #{comment_UID}")
    void updateCommentLike(int comment_UID,int commentLike);
}
