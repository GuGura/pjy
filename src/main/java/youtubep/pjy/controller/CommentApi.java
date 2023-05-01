package youtubep.pjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import youtubep.pjy.domain.LikeComment;
import youtubep.pjy.domain.LikeVideo;
import youtubep.pjy.service.CommentService;
import youtubep.pjy.service.MyPageService;

import javax.servlet.http.HttpSession;

@RestController
public class CommentApi {
    private CommentService commentService;
    private MyPageService myPageService;

    @Autowired
    public CommentApi(CommentService commentService, MyPageService myPageService) {
        this.commentService = commentService;
        this.myPageService = myPageService;
    }

    class ResCommentLike{
        public boolean like;
        public int likeCnt;

        public ResCommentLike(boolean like, int likeCnt) {
            this.like = like;
            this.likeCnt =likeCnt;
        }
    }

    @PostMapping("/comment/like")
    public ResCommentLike commentLike(@RequestParam("checked") boolean checked, @RequestParam("comment_UIDs") String comment_UIDs, HttpSession session) {
        String str[] = comment_UIDs.split("-");
        int comment_UID = Integer.parseInt(str[2]);
        String userID = (String) session.getAttribute("userID");
        int Video_UID = (int) session.getAttribute("video_UID");
        LikeComment comment = new LikeComment();
        comment.setComment_UID(comment_UID);
        comment.setUserID(userID);
        comment.setVideo_UID(Video_UID);
        commentService.updateCommentLike(comment,checked);
        int commentCnt = commentService.getLikeCnt(comment_UID);

        return new ResCommentLike(checked,commentCnt);
    }

    @PostMapping("/video/like")
    public ResCommentLike videoLike(@RequestParam("checked") boolean checked, HttpSession session){
        int video_UID = (int)session.getAttribute("video_UID");
        String userID = (String) session.getAttribute("userID");
        LikeVideo likeVideo = new LikeVideo();
        likeVideo.setVideo_UID(video_UID);
        likeVideo.setUserID(userID);
        int videoLikeCnt = myPageService.updateVideoLike(likeVideo,checked);
        return new ResCommentLike(checked,videoLikeCnt);
    }
}
