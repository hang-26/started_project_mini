package network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitUtils {

    private Retrofit retrofit;


    private  static  RetrofitUtils instance;
    private  RetrofitUtils()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(API.API_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public  static  RetrofitUtils getInstance()
    {
        if(instance==null)
        {
            instance = new RetrofitUtils();
        }
        return instance;

    }
    public  <ServiceClass> ServiceClass createService(Class<ServiceClass>  serviceClassClass)
    {
        return  retrofit.create(serviceClassClass);
    }
}
