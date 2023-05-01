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

    @Select("SELECT * FROM VIDEO order by Video_Like desc")
    List<Video> findAll2();
}
