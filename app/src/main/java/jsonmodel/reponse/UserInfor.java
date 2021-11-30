package jsonmodel.reponse;

import com.google.gson.annotations.SerializedName;

public class UserInfor {

    @SerializedName("userId")
    private  String userId;
    @SerializedName("username")
    private String username;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("avatarUrl")
    private String avatar;

    public UserInfor(String userId, String username, String fullName, String avatar) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.avatar = avatar;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfor{" +
                "userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", avatar='" + avatar + '\'' +
                ",username='" + username + '\''+
                '}';
    }
}
