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

    @Select("SELECT * FROM VIDEO order by Views desc")
    List<Video> findAll3();

    @Select("SELECT count(*)\n" +
            "from USERS a\n" +
            "LEFT JOIN (\n" +
            "    SELECT * FROM VIDEO\n" +
            ")\n" +
            "b ON a.userID = b.Upload_User\n" +
            "WHERE CHANNEL_NAME Like #{search}")
    int findUploader(String search);

    @Select("SELECT b.* ,a.Icon_URL,a.CHANNEL_NAME\n" +
            "from USERS a\n" +
            "LEFT JOIN (\n" +
            "    SELECT * FROM VIDEO\n" +
            ")\n" +
            "b ON a.userID = b.Upload_User\n" +
            "WHERE CHANNEL_NAME Like #{search}")
    List<Video> findUploaderVideo(String search);

    @Select("SELECT b.* ,a.Icon_URL,a.CHANNEL_NAME\n" +
            "from USERS a\n" +
            "LEFT JOIN (\n" +
            "    SELECT * FROM VIDEO\n" +
            ")\n" +
            "b ON a.userID = b.Upload_User\n" +
            "WHERE Video_Name Like '%'|| #{search}|| '%'")
    List<Video> findSearchVideo(String search);
}
