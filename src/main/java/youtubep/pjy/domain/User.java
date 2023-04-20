package youtubep.pjy.domain;

import java.util.Date;

public class User {
    private int user_UID;
    private String userID;
    private String password;
    private String email;
    private String channel_name;
    private byte[] channel_banner;

    public byte[] getChannel_banner() {
        return channel_banner;
    }

    public void setChannel_banner(byte[] channel_banner) {
        this.channel_banner = channel_banner;
    }

    private String description;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private Date join_date;
    private String checkEmail;

    public int getUser_UID() {
        return user_UID;
    }

    public void setUser_UID(int user_UID) {
        this.user_UID = user_UID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

    public String getCheckEmail() {
        return checkEmail;
    }

    public void setCheckEmail(String checkEmail) {
        this.checkEmail = checkEmail;
    }
}
