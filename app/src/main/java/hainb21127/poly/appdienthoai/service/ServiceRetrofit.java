package hainb21127.poly.appdienthoai.service;

import hainb21127.poly.appdienthoai.API_URL;
import hainb21127.poly.appdienthoai.inter.ServiceSignin;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceRetrofit {
    private static ServiceSignin serviceSignin;

    public static ServiceSignin getServiceSignin(){
        if(serviceSignin == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL.POST_SIGN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            serviceSignin = retrofit.create(ServiceSignin.class);
        }
        return serviceSignin;
    }
}
