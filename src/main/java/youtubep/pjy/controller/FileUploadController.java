package youtubep.pjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import youtubep.pjy.domain.Video;
import youtubep.pjy.service.MyPageService;
import youtubep.pjy.service.VideoUploadService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    //System.getProperty("user.dir")는 현재 작업중인 프로젝트 디렉토리의 경로를 나타내는 시스템 속성 값에 속하며 상수이다.
    public static String uploadDirectory = "\\upload\\";
    private final VideoUploadService videoUploadService;
    private final MyPageService pageService;
    @Autowired
    public FileUploadController(VideoUploadService videoUploadService, MyPageService pageService) {
        this.videoUploadService = videoUploadService;
        this.pageService = pageService;
    }


    @PostMapping ("/uploadVideo")
    public String uploading(@RequestParam("video") MultipartFile videoFile,
                            @RequestParam("SumNImg") MultipartFile SumNImg,
                            @RequestParam("videoName") String videoName,
                            @RequestParam("videoContent") String videoContent,
                            HttpSession session){
        Video video = new Video();
        String uploader = (String)session.getAttribute("userID");
        video.setUpload_User(uploader);
        video.setVideo_Name(videoName);
        video.setVideo_Description(videoContent);
        String userid = uploadDirectory + session.getAttribute("userID");
        new File(userid).mkdirs();
        Path vfNameAndPath = Paths.get(userid,videoFile.getOriginalFilename());
        Path siNameAndPath = Paths.get(userid,SumNImg.getOriginalFilename());
        video.setVideo_SumName_URL(siNameAndPath.toString());
        video.setVideo_URL(vfNameAndPath.toString());
        int video_UID = videoUploadService.uploadVideo(video);

        try {
            Video thisVideo = pageService.findVideo(video_UID,uploader);
            System.out.println(thisVideo.getVideo_URL());
            System.out.println(thisVideo.getVideo_SumName_URL());
            session.setAttribute("thisVideo",thisVideo);
            Files.write(vfNameAndPath,videoFile.getBytes());
            Files.write(siNameAndPath,SumNImg.getBytes());
        }catch (IOException e){
            System.out.println("파일업로드에 실패했습니다. FileUploadController/uploadVideo");
        }

        return "bbs";//그냥 url만들고 거기로 이동시켜라
    }

}
