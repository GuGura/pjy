package youtubep.pjy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import youtubep.pjy.domain.Video;

@Mapper
public interface VideoUploadMapper {

    @Insert("INSERT INTO VIDEO (Video_UID,Upload_User,Video_SumName_URL,Video_URL,Video_Name,Video_Description) " +
            "VALUES (video_UID_seq.NEXTVAL,#{upload_User}, #{video_SumName_URL},#{video_URL},#{video_Name},#{video_Description} )")
    void Save(Video video);

    //void Delete(Video video);

    @Select("SELECT * FROM VIDEO where Upload_User = #{upload_User} and Video_Name = #{video_Name} and Video_Description = #{video_Description} and Video_URL = #{video_URL}")
    Video findVideo(Video video);

    @Select("SELECT Video_UID FROM VIDEO where Upload_User = #{upload_User} and Video_Name = #{video_Name} and Video_Description = #{video_Description} and Video_URL = #{video_URL}")
    int findVideoUID(Video video);

    @Update("update video set Video_URL = #{video_URL},Video_SumName_URL = #{video_SumName_URL} where Video_UID = #{video_UID}")
    void updateVideo(Video video);
}
