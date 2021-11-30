package utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
    private static  final String USERNAME_KEY = "username_key";
    private static  final String PASSWORD_KEY = "password_key";
    private static  final String REPASSWORD_KEY = "Repassword_key";
    private static final String USER_ID = "user_id";
    private static final String USER_AVT = "user_avt";
    private SharedPreferences sharedPreferences;

    private static  PreferenceUtil instance;
    public PreferenceUtil(Context context) {
        sharedPreferences = context.getSharedPreferences("sign", Context.MODE_PRIVATE);
    }

    public  static  PreferenceUtil getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new PreferenceUtil(context);
        }
        return instance;
    }


    public  void addUsername(String username)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_KEY, username);
        editor.apply();
    }

    public String getUsername()
    {
        return sharedPreferences.getString(USERNAME_KEY,"");
    }


    public void removeUsername()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USERNAME_KEY);
        editor.apply();
    }



    public  void addPassword(String password)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PASSWORD_KEY, password);
        editor.apply();
    }


    public String getPasswordKey()
    {
        return sharedPreferences.getString(PASSWORD_KEY,"");
    }

    public void removePassword()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PASSWORD_KEY);
        editor.apply();
    }


    public void addUserId(String userid) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, userid);
        editor.apply();
    }


    public String getUserId()
    {
        return sharedPreferences.getString(USER_ID, "");
    }


    public void removeUserId()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USER_ID);
        editor.apply();
    }
    public void addUserAvatar(String useravt) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_AVT, useravt);
        editor.apply();
    }


    public String getUserAvtar()
    {
        return sharedPreferences.getString(USER_AVT, "");
    }


    public void removeUserAvater()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USER_AVT);
        editor.apply();
    }


}
