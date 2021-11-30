package fradment;

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

import com.example.singinapplication.R;
import com.google.gson.Gson;

import java.io.IOException;

import jsonmodel.reponse.RegisterInfor;
import jsonmodel.request.RegisterSendForn;
import network.RetrofisService;
import network.RetrofitUtils;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import utils.Utils;

public class RegisterFragment extends Fragment {
    private final int MOD_NORMAL =0;
    private final int MOD_LOADING =1;

    EditText edtUser;
    EditText edtPass;
    EditText edtEmail;
    EditText edtfullname;
    EditText edtAdress;
    EditText edtBithday;
    EditText edtPhone;
    TextView btnSignup;
    ViewFlipper viewFlipper;
    RetrofisService retrofisService;
    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        addListener();
        retrofisService = RetrofitUtils.getInstance().createService(RetrofisService.class);


    }
    private void init(View view) {
        edtUser = view.findViewById(R.id.edt_username);
        edtPass = view.findViewById(R.id.edt_password);
        edtEmail = view.findViewById(R.id.edt_email);
        edtfullname = view.findViewById(R.id.edt_fullname);
        edtAdress = view.findViewById(R.id.edt_address);
        edtBithday = view.findViewById(R.id.edt_birthday) ;
        edtPhone = view.findViewById(R.id.edt_phone);
        btnSignup = view.findViewById(R.id.tv_btnsignup);
        viewFlipper = view.findViewById(R.id.view_flipperr);
    }

    private void addListener()
    {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = edtUser.getText().toString();
                String Password = edtPass.getText().toString();
                String Repass = edtEmail.getText().toString();
                String Fullname = edtfullname.getText().toString();
                String Adress = edtAdress.getText().toString();
                String Bithday = edtBithday.getText().toString();
                String Phone = edtPhone.getText().toString();
//                PreferenceUtil.getInstance(getContext()).addUsername(Username);
//                PreferenceUtil.getInstance(getContext()).addPassword(Password);
//                Toast.makeText(getContext(),"Your registered success",Toast.LENGTH_SHORT).show();
                if(Username.isEmpty())
                {
                    Utils.showToast(getContext(),"Writing username");
                }
                else if(Password.isEmpty())
                {
                    Utils.showToast(getContext(),"Writing pass");
                }
                else if(Repass.isEmpty())
                {
                    Utils.showToast(getContext(),"Re password");
                }
                else if (Fullname.isEmpty())
                {
                    Utils.showToast(getContext(),"Your full name");
                }
                else if(Adress.isEmpty())
                {
                    Utils.showToast(getContext(),"Your address");
                }
                else if(Bithday.isEmpty())
                {
                    Utils.showToast(getContext(),"Your birthday");
                }
                else if(Phone.isEmpty())
                {
                    Utils.showToast(getContext(),"Your phone");
                }

                else

                {
                    register(Username,Password,Repass,Fullname,Adress,Bithday,Phone);
                }
            }
        });
    }




    private void register(String username, String password, String email, String fullname, String adress,String birthday, String phone)
    {
        RegisterSendForn sendForn = new RegisterSendForn(username,password,email,fullname,adress,birthday,phone);
        viewFlipper.setDisplayedChild(MOD_LOADING);

        retrofisService._register(sendForn).enqueue(new Callback<RegisterInfor>() {
            @Override
            public void onResponse(Response<RegisterInfor> response, Retrofit retrofit) {
                RegisterInfor registerInfor = response.body();

                if(response.code()==200 && registerInfor != null)
                {
                    Utils.showToast(getContext(),"Success");
                }
                else
                {
                    //login false


                    try {
                        Gson gson = new Gson();
                        RegisterInfor errorResponse = gson.fromJson(
                                response.errorBody().string(),
                                RegisterInfor.class);
                        String err = errorResponse.getErr();
                        Utils.showToastlong(getContext(),err);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    Utils.showToast(getContext(),"Fall");
                    viewFlipper.setDisplayedChild(MOD_NORMAL);

                }
            }

            @Override
            public void onFailure(Throwable t) {
                Utils.showToast(getContext(),"Fall");
                viewFlipper.setDisplayedChild(MOD_NORMAL);

            }
        });

    }


}