package youtubep.pjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youtubep.pjy.domain.Video;
import youtubep.pjy.mapper.MainPublicMapper;
import youtubep.pjy.mapper.MyPageMapper;

import java.util.List;

@Service
public class MainPublicService {

    private final MainPublicMapper mainPublicMapper;
    private final MyPageMapper myPageMapper;

    @Autowired
    public MainPublicService(MainPublicMapper mainPublicMapper, MyPageMapper myPageMapper) {
        this.mainPublicMapper = mainPublicMapper;
        this.myPageMapper = myPageMapper;
    }

    public List<Video> findAll(){
        List<Video> video = mainPublicMapper.findAll();
        for(int i = 0 ; i<video.size();i++){
            int video_UID = video.get(i).getVideo_UID();
            String upload_userIcon = myPageMapper.findUpload_userIcon(video_UID);
            String channel_name = myPageMapper.findUpload_channelName(video_UID);
            video.get(i).setIcon_URL(upload_userIcon);
            video.get(i).setChannel_name(channel_name);
        }
        return video;
    }
}