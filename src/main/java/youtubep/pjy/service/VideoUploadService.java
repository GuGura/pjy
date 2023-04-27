package youtubep.pjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import youtubep.pjy.domain.Video;
import youtubep.pjy.mapper.VideoUploadMapper;

@Controller
public class VideoUploadService {

    private final VideoUploadMapper videoUploadMapper;

    @Autowired
    public VideoUploadService(VideoUploadMapper videoUploadMapper) {
        this.videoUploadMapper = videoUploadMapper;
    }

    /**
     * 비디오 업로드
     * @param video
     */
    public int uploadVideo(Video video) {
        videoUploadMapper.Save(video);
        return findVideoUID(video);
    }

    public Video findVideo(Video video) {
        return videoUploadMapper.findVideo(video);
    }
    public int findVideoUID(Video video) {
        return videoUploadMapper.findVideoUID(video);
    }


    /**
     * 비디오 삭제
     */

    /**
     * 형식검사?
     */
}
