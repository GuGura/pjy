package youtubep.pjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import youtubep.pjy.domain.Comment;
import youtubep.pjy.domain.Video;
import youtubep.pjy.service.CommentService;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/updateComment")
    public String updateComment(@RequestParam("comment") String Video_comment, HttpSession session){
        Video video = (Video) session.getAttribute("thisVideo");
        String userID = (String)session.getAttribute("userID");
        Comment comment = new Comment();
        comment.setVideo_UID(video.getVideo_UID());
        comment.setComment_UserID(userID);
        comment.setComment_Description(Video_comment);
        commentService.updateComment(comment);
        return "s";
    }
}
