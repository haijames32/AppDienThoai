package hainb21127.poly.appdienthoai.inter;

import hainb21127.poly.appdienthoai.API_URL;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit;
    public static Retrofit getService(){
       return retrofit = new Retrofit.Builder()
                .baseUrl(API_URL.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}

