package youtubep.pjy.domain;

import java.util.Date;

public class LikeVideo {
    private int video_UID;
    private String userID;
    private Date videoLike_Join_Date;

    public int getVideo_UID() {
        return video_UID;
    }

    public void setVideo_UID(int video_UID) {
        this.video_UID = video_UID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getVideoLike_Join_Date() {
        return videoLike_Join_Date;
    }

    public void setVideoLike_Join_Date(Date videoLike_Join_Date) {
        this.videoLike_Join_Date = videoLike_Join_Date;
    }
}
