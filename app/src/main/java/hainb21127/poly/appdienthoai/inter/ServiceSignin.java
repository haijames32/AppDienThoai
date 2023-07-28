package hainb21127.poly.appdienthoai.inter;

import android.database.Observable;

import hainb21127.poly.appdienthoai.model.ResponseData;
import hainb21127.poly.appdienthoai.model.User;
import hainb21127.poly.appdienthoai.model.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceSignin {
    @POST("api/signin")
    Call<UserLogin> postSignin(@Body String username, String passwd);

    @POST("api/register")
    Call<User> registerUser(@Body User data);
}
