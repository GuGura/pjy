package youtubep.pjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import youtubep.pjy.domain.Video;
import youtubep.pjy.mapper.CommentMapper;
import youtubep.pjy.mapper.VideoUploadMapper;

@Controller
public class VideoUploadService {

    private final VideoUploadMapper videoUploadMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public VideoUploadService(VideoUploadMapper videoUploadMapper, CommentMapper commentMapper) {
        this.videoUploadMapper = videoUploadMapper;
        this.commentMapper = commentMapper;
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
    public void deleteVideo(int video_UID){
        videoUploadMapper.deleteVideo(video_UID);
        commentMapper.deleteComment(video_UID);
    }
    /**
     * 형식검사?
     */
}
