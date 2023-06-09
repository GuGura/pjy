package youtubep.pjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youtubep.pjy.domain.Video;
import youtubep.pjy.mapper.MainPublicMapper;
import youtubep.pjy.mapper.MyPageMapper;

import java.util.ArrayList;
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

    public List<Video> findAll(String category){
        List<Video> video = new ArrayList<>();
       if(category.equals("popular")){
            video = mainPublicMapper.findAll2();
        }else if (category.equals("view")){
           video = mainPublicMapper.findAll3();
       }else if(category.equals("old")){
           video = mainPublicMapper.findAll4();
       }
        else{
            video = mainPublicMapper.findAll();
        }
        for(int i = 0 ; i<video.size();i++){
            int video_UID = video.get(i).getVideo_UID();
            String upload_userIcon = myPageMapper.findUpload_userIcon(video_UID);
            String channel_name = myPageMapper.findUpload_channelName(video_UID);
            int VideoLikeCtn = myPageMapper.VideoLikeCtn(video_UID);
            myPageMapper.insertVideoLikeCtn(video_UID,VideoLikeCtn);
            video.get(i).setIcon_URL(upload_userIcon);
            video.get(i).setChannel_name(channel_name);
            video.get(i).setVideo_Like(VideoLikeCtn);
        }
        return video;
    }

    public int isUploaderExist(String search){
        return mainPublicMapper.findUploader(search);
    }

    public List<Video> findUploaderVideo(String search){
        return mainPublicMapper.findUploaderVideo(search);
    }

    public List<Video> findSearchVideo(String search,List<Video> searchUploader){
        List<Video> list = new ArrayList<>();
        System.out.println("searchUploader:"+searchUploader.size());
        if(searchUploader.size()== 0){
            return mainPublicMapper.findSearchVideo(search);
        }else{
            List<Video> searchVideo = mainPublicMapper.findSearchVideo(search);
            boolean isSame;
            for (int i = 0; i < searchVideo.size(); i++) {
                isSame = false;
                for (int j = 0; j < searchUploader.size(); j++) {
                    if(searchVideo.get(i).getVideo_UID() == searchUploader.get(j).getVideo_UID()){
                        isSame = true;
                        break;
                    }
                    isSame = false;
                }
                if (isSame == false){
                    list.add(searchVideo.get(i));
                }
            }
            return list;
        }
    }

}
