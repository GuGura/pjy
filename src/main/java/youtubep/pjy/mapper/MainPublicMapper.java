package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import youtubep.pjy.domain.User;
import youtubep.pjy.domain.Video;

import java.util.List;

@Mapper
public interface MainPublicMapper {

    @Select("SELECT * FROM VIDEO")
    List<Video> findAll();
}
