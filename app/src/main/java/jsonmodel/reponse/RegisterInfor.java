package jsonmodel.reponse;
import com.google.gson.annotations.SerializedName;
public class RegisterInfor {
    @SerializedName("userId")
    private String userId;
    @SerializedName("username")
    private String username;
    @SerializedName("fullname")
    private  String fullname;
    @SerializedName("avatarUrl")
    private String avatarUrl;
    @SerializedName("err")
    private  String err;

    public RegisterInfor(String userId, String username, String fullname, String avatarUrl, String err) {
        this.userId = userId;
        this.username = username;
        this.fullname = fullname;
        this.avatarUrl = avatarUrl;
        this.err = err;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
