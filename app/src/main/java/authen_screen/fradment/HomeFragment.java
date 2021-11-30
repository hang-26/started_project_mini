package authen_screen.fradment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.singinapplication.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.StatusAdapter;
import de.hdodenhof.circleimageview.CircleImageView;
import inteface.OnitemStatusClickListener;
import jsonmodel.reponse.RegisterInfor;
import jsonmodel.reponse.Status;
import jsonmodel.request.CreateStatusSendForm;
import jsonmodel.request.LikeStatusSendForm;
import network.RetrofisService;
import network.RetrofitUtils;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import utils.PreferenceUtil;
import utils.Utils;


public class HomeFragment extends Fragment implements OnitemStatusClickListener {
    private RetrofisService retrofisService;
    ViewFlipper viewFlipper;
    CircleImageView imvAvatar;
    EditText edtContent;
    ImageView imvSend;
    RecyclerView recyclerView;
    ArrayList<Status> statusesList;
    StatusAdapter statusAdapter;

    SwipeRefreshLayout sRL_refresh;

    public HomeFragment() {
        // Required empty public constructor
    }
    String userId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        addListener();
        userId =  PreferenceUtil.getInstance(getContext()).getUserId();
        getallpost();
    }



    private  void init(View view)
    {

        retrofisService = RetrofitUtils.getInstance().createService(RetrofisService.class);
        viewFlipper = view.findViewById(R.id.view_flipper);
        edtContent = view.findViewById(R.id.edt_content);
        imvSend =  view.findViewById(R.id.imv_send);
        imvAvatar = view.findViewById(R.id.imv_avatar);
        recyclerView = view.findViewById(R.id.recyclerview);
        sRL_refresh = view.findViewById(R.id.sRL_refresh);

        statusesList = new ArrayList<>();
        statusAdapter = new StatusAdapter(statusesList, this);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(statusAdapter);

        sRL_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getallpost();
            }
        });

    }
    private void addListener()
    {
        imvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edtContent.getText().toString();
                if(content.isEmpty())
                {
                    Utils.showToast(getContext(),"yêu cầu nhập nội dung");
                }
                else
                {
                    createPost(content);

                }
            }
        });    }


    private void getallpost()
    {
        retrofisService.getallpost(userId).enqueue(new Callback<List<Status>>() {
            @Override
            public void onResponse(Response<List<Status>> response, Retrofit retrofit) {

                sRL_refresh.setRefreshing(false);
               ArrayList<Status>  statuses = (ArrayList<Status>) response.body();
                if(response.code()==200&& statuses != null)
                {
                   String useravtar = PreferenceUtil.getInstance(getContext()).getUserAvtar();
                    Glide.with(getContext()).load(useravtar).into(imvAvatar);
                    statusesList.clear();
                    statusesList.addAll(statuses);
                   statusAdapter.notifyDataSetChanged();
                   viewFlipper.setDisplayedChild(3);
                }
                else if(statuses.isEmpty())
                {
                    viewFlipper.setDisplayedChild(1);
                }

                else
                {
                    viewFlipper.setDisplayedChild(2);
                    Log.d("bk", "unsuccess");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                sRL_refresh.setRefreshing(false);

                viewFlipper.setDisplayedChild(2);
                Log.d("bk", "unsuccess");
            }
        });
    }
    private  void createPost(String content)
    {
        CreateStatusSendForm createStatusSendForm = new CreateStatusSendForm(userId,content);

        retrofisService._createPost(createStatusSendForm).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Response<Status> response, Retrofit retrofit) {
                Status status = response.body();
                if(response.code()==200 && status != null) {
                    statusesList.add(0,status);
                    statusAdapter.notifyDataSetChanged();
                    edtContent.setText("");

                } else {
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
                    Utils.showToast(getActivity(),"Đăng baì thất bại!");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Utils.showToast(getActivity(),"Đăng baì thất bại!");

            }

        });
    }
    public  void likePost(Status status)
    {

        LikeStatusSendForm likePost = new LikeStatusSendForm(userId,status.getPostId());
        retrofisService._likepost(likePost).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                if(response.code()==200)
                {
                    if(status.isLike())
                    {
                        Utils.showToast(getActivity(),"you unlike post success");
                    }
                   else
                       {
                        Utils.showToast(getActivity(),"you like post success");
                    }

                }
                else {
                    Utils.showToast(getActivity(),"Lỗi!!!!!!");
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Utils.showToast(getActivity(),"Vui lòng kiểm tra lại kết nối mạng");

            }
        });
    }

    @Override
    public void onLikeClick(Status status) {
        likePost(status);
        
    }
}