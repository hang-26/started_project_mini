package authen_screen.fradment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.singinapplication.R;
import utils.Utils;

public class RegisterFragment extends Fragment {

    EditText edtUser;
    EditText edtPass;
    EditText edtEmail;
    EditText edtfullname;
    EditText edtAdress;
    EditText edtBirthday;
    EditText edtPhone;
    TextView btnSignup;
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


    }
    private void init(View view) {
        edtUser = view.findViewById(R.id.edt_username);
        edtPass = view.findViewById(R.id.edt_password);
        edtEmail = view.findViewById(R.id.edt_email);
        edtfullname = view.findViewById(R.id.edt_fullname);
        edtAdress = view.findViewById(R.id.edt_address);
        edtBirthday = view.findViewById(R.id.edt_birthday);
        edtPhone = view.findViewById(R.id.edt_phone);
        btnSignup = view.findViewById(R.id.tv_btnsignup);
    }

    private void addListener()
    {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = edtUser.getText().toString();
                String Password = edtPass.getText().toString();
                String Email = edtEmail.getText().toString();
                String Fullname = edtfullname.getText().toString();
                String Adress = edtAdress.getText().toString();
                String Birthday = edtBirthday.getText().toString();
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
                else if(Email.isEmpty())
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
                else if(Birthday.isEmpty())
                {
                    Utils.showToast(getContext(),"Your birthday");
                }
                else if(Phone.isEmpty())
                {
                    Utils.showToast(getContext(),"Your phone");
                }
                else

                {
                    register(Username,Password,Email,Fullname,Adress,Phone);
                }
            }
        });
    }




    private void register(String username, String password, String repass, String fullname, String adress, String phone) {
    }


}