package authen_screen.fradment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.singinapplication.R;
import utils.Utils;

import authen_screen.HomeActivity;
import utils.PreferenceUtil;

public class LoginFragment extends Fragment {
    EditText edtUsername;
    EditText edtPassword;
    TextView tvSign;
    String name ;
    String pass ;
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


    }



    private void init(View view)
    {
        edtUsername = view.findViewById(R.id.edt_name);
        edtPassword = view.findViewById(R.id.edt_pass);
        tvSign  = view.findViewById(R.id.tv_btnsign);

    }


    private void addListener()
    {
        tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Use = edtUsername.getText().toString();
                String Pass = edtPassword.getText().toString();
                name = PreferenceUtil.getInstance(getContext()).getUsername();
                pass = PreferenceUtil.getInstance(getContext()).getPasswordKey();
                if(Use.equals(name)&& Pass.equals(pass))
                {
                    Utils.showToast(getContext(),"You success");
                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    startActivity(intent);

                }
                else
                {

                    Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private  void login(String name,String pass)
    {

    }

}