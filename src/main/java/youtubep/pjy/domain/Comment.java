package youtubep.pjy.domain;

import java.util.Date;

public class Comment {
    private int Comment_UID;
    private int Video_UID;
    private String Comment_UserID;
    private String Comment_Description;
    private Date Comment_Join_Date;
    private int Comment_Like;

    private String isChecked; // LIKECOMMENT에서 조인
    private String icon_URL; //Users테이브에서 조인 예정

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public String getIcon_URL() {
        return icon_URL;
    }

    public void setIcon_URL(String icon_URL) {
        this.icon_URL = icon_URL;
    }

    public int getComment_UID() {
        return Comment_UID;
    }

    public void setComment_UID(int comment_UID) {
        Comment_UID = comment_UID;
    }

    public int getVideo_UID() {
        return Video_UID;
    }

    public void setVideo_UID(int video_UID) {
        Video_UID = video_UID;
    }

    public String getComment_UserID() {
        return Comment_UserID;
    }

    public void setComment_UserID(String comment_UserID) {
        Comment_UserID = comment_UserID;
    }

    public String getComment_Description() {
        return Comment_Description;
    }

    public void setComment_Description(String comment_Description) {
        Comment_Description = comment_Description;
    }

    public Date getComment_Join_Date() {
        return Comment_Join_Date;
    }

    public void setComment_Join_Date(Date comment_Join_Date) {
        Comment_Join_Date = comment_Join_Date;
    }

    public int getComment_Like() {
        return Comment_Like;
    }

    public void setComment_Like(int comment_Like) {
        Comment_Like = comment_Like;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "Comment_UID=" + Comment_UID +
                ", Video_UID=" + Video_UID +
                ", Comment_UserID='" + Comment_UserID + '\'' +
                ", Comment_Description='" + Comment_Description + '\'' +
                ", Comment_Join_Date=" + Comment_Join_Date +
                ", Comment_Like=" + Comment_Like +
                ", isChecked='" + isChecked + '\'' +
                ", icon_URL='" + icon_URL + '\'' +
                '}';
    }
}
