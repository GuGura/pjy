package youtubep.pjy.domain;

import java.io.Serializable;
import java.util.Date;

public class Video implements Serializable {
    private int video_UID;
    private String upload_User;
    private String video_SumName_URL;
    private String video_URL;
    private String video_Name;
    private String video_Description;
    private Date video_Join_Date;
    private int video_Like;
    private int views;

    private String icon_URL; // Users 테이블에서 조인해서 들고와라
    private String channel_name; //Users 테이블에서 조인해서 들고와라

    @Override
    public String toString() {
        return "Video{" +
                "video_UID=" + video_UID +
                ", upload_User='" + upload_User + '\'' +
                ", video_SumName_URL='" + video_SumName_URL + '\'' +
                ", video_URL='" + video_URL + '\'' +
                ", video_Name='" + video_Name + '\'' +
                ", video_Description='" + video_Description + '\'' +
                ", video_Join_Date=" + video_Join_Date +
                ", video_Like=" + video_Like +
                ", views=" + views +
                ", icon_URL='" + icon_URL + '\'' +
                ", channel_name='" + channel_name + '\'' +
                '}';
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getIcon_URL() {
        return icon_URL;
    }

    public void setIcon_URL(String icon_URL) {
        this.icon_URL = icon_URL;
    }

    public int getVideo_UID() {
        return video_UID;
    }

    public void setVideo_UID(int video_UID) {
        this.video_UID = video_UID;
    }

    public String getUpload_User() {
        return upload_User;
    }

    public void setUpload_User(String upload_User) {
        this.upload_User = upload_User;
    }

    public String getVideo_SumName_URL() {
        return video_SumName_URL;
    }

    public void setVideo_SumName_URL(String video_SumName_URL) {
        this.video_SumName_URL = video_SumName_URL;
    }

    public String getVideo_URL() {
        return video_URL;
    }

    public void setVideo_URL(String video_URL) {
        this.video_URL = video_URL;
    }

    public String getVideo_Name() {
        return video_Name;
    }

    public void setVideo_Name(String video_Name) {
        this.video_Name = video_Name;
    }

    public String getVideo_Description() {
        return video_Description;
    }

    public void setVideo_Description(String video_Description) {
        this.video_Description = video_Description;
    }

    public Date getVideo_Join_Date() {
        return video_Join_Date;
    }

    public void setVideo_Join_Date(Date video_Join_Date) {
        this.video_Join_Date = video_Join_Date;
    }

    public int getVideo_Like() {
        return video_Like;
    }

    public void setVideo_Like(int video_Like) {
        this.video_Like = video_Like;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
