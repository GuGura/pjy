package youtubep.pjy.domain;

import java.util.Date;

public class LikeComment {
    private int Comment_UID;
    private String UserID;
    private int Video_UID;
    private Date CommentLike_Join_Date;


    public int getVideo_UID() {
        return Video_UID;
    }

    public void setVideo_UID(int video_UID) {
        Video_UID = video_UID;
    }

    public int getComment_UID() {
        return Comment_UID;
    }

    public void setComment_UID(int comment_UID) {
        Comment_UID = comment_UID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public Date getCommentLike_Join_Date() {
        return CommentLike_Join_Date;
    }

    public void setCommentLike_Join_Date(Date commentLike_Join_Date) {
        CommentLike_Join_Date = commentLike_Join_Date;
    }

    @Override
    public String toString() {
        return "LikeComment{" +
                "Comment_UID=" + Comment_UID +
                ", UserID='" + UserID + '\'' +
                ", Video_UID=" + Video_UID +
                ", CommentLike_Join_Date=" + CommentLike_Join_Date +
                '}';
    }
}
