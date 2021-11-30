package fradment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.singinapplication.HomeActivity;
import com.example.singinapplication.R;

import jsonmodel.reponse.UserInfor;
import jsonmodel.request.LoginSendFrom;
import network.RetrofisService;
import network.RetrofitUtils;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import utils.Utils;

import utils.PreferenceUtil;

public class LoginFragment extends Fragment {
    private final int MOD_NORMAL =0;
    private final int MOD_LOADING =1;
    EditText edtUsername;
    EditText edtPassword;
    TextView tvSign;
    String name ;
    String pass ;
    ViewFlipper viewFlipper;
    RetrofisService retrofisService;
    public LoginFragment()
    {
        
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        addListener();
        name = PreferenceUtil.getInstance(getContext()).getUsername();
        pass = PreferenceUtil.getInstance(getContext()).getPasswordKey();
        retrofisService = RetrofitUtils.getInstance().createService(RetrofisService.class);

    }



    private void init(View view)
    {
        edtUsername = view.findViewById(R.id.edt_name);
        edtPassword = view.findViewById(R.id.edt_pass);
        tvSign  = view.findViewById(R.id.tv_btnsign);
        viewFlipper = view.findViewById(R.id.view_flipper);

    }


    private void addListener()
    {
        tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Use = edtUsername.getText().toString();
                String Pass = edtPassword.getText().toString();
                if(Use.isEmpty()||Pass.isEmpty())
                {
                    Utils.showToast(getContext(), " Your must writing username or pass word");
                }
                else
                    {

                    login(Use, Pass);
                }

            }
        });
    }

    private  void login(String name,String pass)
    {
        LoginSendFrom sendFrom = new LoginSendFrom(name,pass);

        viewFlipper.setDisplayedChild(MOD_LOADING);
        retrofisService._login(sendFrom).enqueue(new Callback<UserInfor>() {
            @Override
            public void onResponse(Response<UserInfor> response, Retrofit retrofit) {

                UserInfor userInfor = response.body();


                if(response.code()== 200 && userInfor != null)
                {

                    //login success

                    Log.d("bk", userInfor.toString());

                    String userid = userInfor.getUserId();
                    PreferenceUtil.getInstance(getContext()).addUserId(userid);

                    String useravt = userInfor.getAvatar();
                    PreferenceUtil.getInstance(getContext()).addUserAvatar(useravt);
                    goToHome();
                }
                else
                {
                    //login false
                    Log.d("bk","false");
                }
                viewFlipper.setDisplayedChild(MOD_NORMAL);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("bk","false");
                //login false
                viewFlipper.setDisplayedChild(MOD_NORMAL);

            }
        });

    }
    private void goToHome()
    {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }

}