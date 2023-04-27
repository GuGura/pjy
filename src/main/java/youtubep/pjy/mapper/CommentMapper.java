package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import youtubep.pjy.domain.Comment;
import youtubep.pjy.domain.Video;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM Video_Comment WHERE video_UID = #{video_UID} order by Comment_UID")
    List<Comment> findAll(int video_UID);
}
