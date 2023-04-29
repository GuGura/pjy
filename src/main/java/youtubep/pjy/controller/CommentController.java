package youtubep.pjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import youtubep.pjy.domain.Comment;
import youtubep.pjy.domain.LikeComment;
import youtubep.pjy.domain.Video;
import youtubep.pjy.service.CommentService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/updateComment")
    public String updateComment(@RequestParam("Video_comment") String Video_comment, HttpSession session) {
        Video video = (Video) session.getAttribute("thisVideo");
        String userID = (String) session.getAttribute("userID");
        Comment comment = new Comment();
        comment.setVideo_UID(video.getVideo_UID());
        comment.setComment_UserID(userID);
        comment.setComment_Description(Video_comment);
        commentService.updateComment(comment);
        int video_UID = video.getVideo_UID();
        session.setAttribute("video_UID", video_UID);
        return "redirect:/comment_list";
    }

    @PostMapping("/CommentDelete")
    public String deleteComment(@RequestParam("comment_UID") int comment_UID) {
        commentService.deleteComment(comment_UID);
        return "redirect:/comment_list";
    }

    @GetMapping("/comment_list")
    public String commentList(HttpSession session) {
        int video_UID = (int) session.getAttribute("video_UID");
        List<Comment> comments = commentService.findAll(video_UID);
        session.setAttribute("comments", comments);
        return "comment_list";
    }

    /***
     * 로그인을 반드시 해야 좋아요 버튼을 클릭할 수있게 사전 차단 필요
     * @param checked
     * @param comment_UIDs
     * @param session
     * @return
     */
    @PostMapping("/commentLike")
    public String commentLike(@RequestParam("checked") boolean checked, @RequestParam("comment_UIDs") String comment_UIDs, HttpSession session) {
        String str[] = comment_UIDs.split("-");
        int comment_UID = Integer.parseInt(str[2]);
        String userID = (String) session.getAttribute("userID");
        int Video_UID = (int) session.getAttribute("video_UID");
        LikeComment comment = new LikeComment();
        comment.setComment_UID(comment_UID);
        comment.setUserID(userID);
        comment.setVideo_UID(Video_UID);
        System.out.println(comment_UID);
        System.out.println(userID);
        System.out.println(Video_UID);
        commentService.updateCommentLike(comment,checked);
        return "redirect:/";
    }

}
