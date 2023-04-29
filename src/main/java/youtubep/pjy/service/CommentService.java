package youtubep.pjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youtubep.pjy.domain.Comment;
import youtubep.pjy.domain.LikeComment;
import youtubep.pjy.mapper.CommentMapper;

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
    public List<LikeComment> findLikeComments(int video_UID){
        return commentMapper.findlikeCommentss(video_UID);
    }

    public List<Comment> findAll(int video_UID){
        List<Comment> comments = commentMapper.findAll(video_UID);
        for (int i = 0; i < comments.size(); i++) {
            String userID = comments.get(i).getComment_UserID();
            String Icon_URL = commentMapper.findIcon_URL(userID);
            int commentLike = commentMapper.findLikeCount(comments.get(i).getComment_UID());
            comments.get(i).setComment_Like(commentLike);
            comments.get(i).setIcon_URL(Icon_URL);
        }
        return comments;
    }
}
