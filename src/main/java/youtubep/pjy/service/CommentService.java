package youtubep.pjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youtubep.pjy.domain.Comment;
import youtubep.pjy.domain.LikeComment;
import youtubep.pjy.mapper.CommentMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public void updateComment(Comment comment){
        commentMapper.save(comment);
    }
    public void updateCommentLike(LikeComment likeComment,boolean check){
        if (check == false){
            System.out.println(likeComment.toString());
            commentMapper.saveCommentLike(likeComment);
        }
        else if (check == true)
        {
            commentMapper.deleteCommetLike(likeComment);
        }
    }
    public void deleteComment(int comment_UID){
        commentMapper.deleteComment(comment_UID);
    }


    public List<Comment> findAll(int video_UID,String userID){
        List<Comment> comments = commentMapper.findAll(video_UID);
        List<String> likeComments = new ArrayList<>();
        System.out.println(userID);
        if(userID !=null){
            likeComments = commentMapper.findlikeCommentss(video_UID,userID);
        }
        for (int i = 0; i < comments.size(); i++) {
            String CommentUserID = comments.get(i).getComment_UserID();
            String Icon_URL = commentMapper.findIcon_URL(CommentUserID);
            int commentLike = commentMapper.findLikeCount(comments.get(i).getComment_UID());
            commentMapper.updateCommentLike(comments.get(i).getComment_UID(),commentLike);
            comments.get(i).setComment_Like(commentLike);
            comments.get(i).setIcon_URL(Icon_URL);
            if(userID !=null){
                comments.get(i).setIsChecked(likeComments.get(i));
            }
            System.out.println(comments.get(i).toString());
        }
        return comments;
    }

    public int getLikeCnt(int Comment_UID) {
        return commentMapper.findLikeCount(Comment_UID);
    }
}
