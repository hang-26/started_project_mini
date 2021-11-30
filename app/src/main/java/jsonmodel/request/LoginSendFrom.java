package jsonmodel.request;

import com.google.gson.annotations.SerializedName;

public class LoginSendFrom {
    @SerializedName("username")
    private String Username;
    @SerializedName("password")
    private  String Password;

    public LoginSendFrom(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
