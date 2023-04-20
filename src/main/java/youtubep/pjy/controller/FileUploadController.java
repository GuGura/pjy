package youtubep.pjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import youtubep.pjy.domain.Video;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    //System.getProperty("user.dir")는 현재 작업중인 프로젝트 디렉토리의 경로를 나타내는 시스템 속성 값에 속하며 상수이다.
    public static String uploadDirectory = System.getProperty("user.dir") + "/upload";

    @PostMapping("/uploadVideo")
    public String uploading(@RequestParam("video") MultipartFile videoFile,
                            @RequestParam("SumNImg") MultipartFile SumNImg,
                            @RequestParam("videoName") String videoName,
                            @RequestParam("videoContent") String videoContent,
                            HttpSession session){
        Video video = new Video();
        video.setVideo_Name(videoName);
        video.setVideo_Description(videoContent);
        String userid = uploadDirectory +"/"+ session.getAttribute("userID");
        new File(userid).mkdir();
        System.out.println("userid path :"+userid);
        Path vfNameAndPath = Paths.get(userid,videoFile.getOriginalFilename());
        Path siNameAndPath = Paths.get(userid,SumNImg.getOriginalFilename());
        System.out.println("vfNameAndPath path :"+ vfNameAndPath);
        System.out.println("siNameAndPath path :"+ siNameAndPath);
        try {
            Files.write(vfNameAndPath,videoFile.getBytes());
            Files.write(siNameAndPath,SumNImg.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        return "bbs";
    }

}
