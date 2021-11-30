package network;

import java.util.List;

import jsonmodel.reponse.RegisterInfor;
import jsonmodel.reponse.UserInfor;
import jsonmodel.reponse.Status;
import jsonmodel.request.CreateStatusSendForm;
import jsonmodel.request.LikeStatusSendForm;
import jsonmodel.request.LoginSendFrom;
import jsonmodel.request.RegisterSendForn;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface RetrofisService {


    @POST(API.LOGIN)
    @Headers({API.HEADER})
    Call<UserInfor> _login(@Body LoginSendFrom sendFrom);


    @POST(API.REGISTER)
    @Headers({API.HEADER})
    Call<RegisterInfor>_register(@Body RegisterSendForn sendForn);

    @GET(API.GET_ALL_POST)
    @Headers({API.HEADER})
    Call<List<Status>>getallpost(@Query("userId")String userid);

    @POST(API.CREATE_POST)
    @Headers({API.HEADER})
    Call<Status>_createPost(@Body CreateStatusSendForm createStatusSendForm);
    @POST(API.LIKE_POST)
    @Headers({API.HEADER})
    Call<Void>_likepost(@Body LikeStatusSendForm likePost);
}
