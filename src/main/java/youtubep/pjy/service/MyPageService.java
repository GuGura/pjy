package youtubep.pjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youtubep.pjy.domain.LikeVideo;
import youtubep.pjy.domain.User;
import youtubep.pjy.domain.Video;
import youtubep.pjy.mapper.MyPageMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyPageService {

    private final MyPageMapper myPageMapper;

    @Autowired
    public MyPageService(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }

    public User goMyPage(String userID) {
        return myPageMapper.findMyInfo(userID);
    }

    public String checkPassword(User user) {
        int result = myPageMapper.checkPassword(user);
        if (result == 1)
            return "bbs";
        else
            return "checkPassword";
    }

    public void update(User user) {
        myPageMapper.update(user);
    }

    public List<Video> myVideos(String userID) {
        return myPageMapper.findMyVideos(userID);
    }

    public Video findVideo(int video_UID,String userID) {
        String ischecked = "";
        myPageMapper.updateViews(video_UID);
        Video video =  myPageMapper.findVideo(video_UID);
        int VideoLikeCtn = myPageMapper.VideoLikeCtn(video_UID);
        String upload_userIcon = myPageMapper.findUpload_userIcon(video_UID);
        String channel_name = myPageMapper.findUpload_channelName(video_UID);
        if(userID.length() >= 1){
            System.out.println(video_UID);
            ischecked = myPageMapper.findisChecked(video_UID,userID);
        }
        video.setIcon_URL(upload_userIcon);
        video.setChannel_name(channel_name);
        video.setVideo_Like(VideoLikeCtn);
        video.setIsChecked(ischecked);
        return video;
    }

    public int updateVideoLike(LikeVideo likeVideo, boolean checked){
        if (checked == false){
            myPageMapper.saveVideoLikeCtn(likeVideo);
        }else if(checked == true){
            myPageMapper.deleteVideoLikeCtn(likeVideo);
        }
        return myPageMapper.VideoLikeCtn(likeVideo.getVideo_UID());
    }

    public List<String> getURL(User user){
        User demo = myPageMapper.findURL(user);
        List<String> list = new ArrayList<>();
        list.add(demo.getIcon_URL());
        list.add(demo.getBanner_URL());
        return list;
    }
}
