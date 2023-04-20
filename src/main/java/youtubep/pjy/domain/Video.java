package youtubep.pjy.domain;

import java.util.Date;

public class Video {
    private int Video_UID;
    private String Upload_User;
    private String Video_SumName_URL;
    private String Video_URL;
    private String Video_Name;
    private String Video_Description;
    private Date Video_Join_Date;
    private int Video_Like;
    private int Views;

    public int getVideo_UID() {
        return Video_UID;
    }

    public void setVideo_UID(int video_UID) {
        Video_UID = video_UID;
    }

    public String getUpload_User() {
        return Upload_User;
    }

    public void setUpload_User(String upload_User) {
        Upload_User = upload_User;
    }

    public String getVideo_SumName_URL() {
        return Video_SumName_URL;
    }

    public void setVideo_SumName_URL(String video_SumName_URL) {
        Video_SumName_URL = video_SumName_URL;
    }

    public String getVideo_URL() {
        return Video_URL;
    }

    public void setVideo_URL(String video_URL) {
        Video_URL = video_URL;
    }

    public String getVideo_Name() {
        return Video_Name;
    }

    public void setVideo_Name(String video_Name) {
        Video_Name = video_Name;
    }

    public String getVideo_Description() {
        return Video_Description;
    }

    public void setVideo_Description(String video_Description) {
        Video_Description = video_Description;
    }

    public Date getVideo_Join_Date() {
        return Video_Join_Date;
    }

    public void setVideo_Join_Date(Date video_Join_Date) {
        Video_Join_Date = video_Join_Date;
    }

    public int getVideo_Like() {
        return Video_Like;
    }

    public void setVideo_Like(int video_Like) {
        Video_Like = video_Like;
    }

    public int getViews() {
        return Views;
    }

    public void setViews(int views) {
        Views = views;
    }
}
