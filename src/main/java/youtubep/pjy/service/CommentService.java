package youtubep.pjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youtubep.pjy.domain.Comment;
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

    }
    public List<Comment> findAll(int video_UID){
        return commentMapper.findAll(video_UID);
    }
}
