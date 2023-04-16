package youtubep.pjy.controller.mypage;

public class MyPageInfo {
    private String userID;;
    private int Channel_id;
    private String Chanel_Name;
    private String description;
    private int views;
    private String location;
    private String Propel;
    private boolean show_channel;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getChannel_id() {
        return Channel_id;
    }

    public void setChannel_id(int channel_id) {
        Channel_id = channel_id;
    }

    public String getChanel_Name() {
        return Chanel_Name;
    }

    public void setChanel_Name(String chanel_Name) {
        Chanel_Name = chanel_Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPropel() {
        return Propel;
    }

    public void setPropel(String propel) {
        Propel = propel;
    }

    public boolean isShow_channel() {
        return show_channel;
    }

    public void setShow_channel(boolean show_channel) {
        this.show_channel = show_channel;
    }
}
