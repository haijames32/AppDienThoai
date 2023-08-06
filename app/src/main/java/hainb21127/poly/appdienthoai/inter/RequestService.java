package hainb21127.poly.appdienthoai.inter;

import java.util.List;

import hainb21127.poly.appdienthoai.model.AddOrder;
import hainb21127.poly.appdienthoai.model.BaseRequestSearch;
import hainb21127.poly.appdienthoai.model.BaseRequestSignin;
import hainb21127.poly.appdienthoai.model.BaseResponseSignUp;
import hainb21127.poly.appdienthoai.model.BaseResponseSignin;
import hainb21127.poly.appdienthoai.model.Order;
import hainb21127.poly.appdienthoai.model.Product;
import hainb21127.poly.appdienthoai.model.Theloai;
import hainb21127.poly.appdienthoai.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RequestService {

    @POST("api/signin")
    Call<BaseResponseSignin> signinUser(@Body BaseRequestSignin data);
    @POST("api/register")
    Call<BaseResponseSignUp> registerUser(@Body User data);

    @PUT("api/users/edit/{id}")
    Call<User> editUser(@Path("id") String id, @Body User data);

    @GET("api/cats")
    Call<List<Theloai>> getCategory();

    @GET("api/products")
    Call<List<Product>> getProduct();

    @GET("api/orders/{id_khachhang}")
    Call<List<Order>> getOrder(@Path("id_khachhang") String id_khachhang);

    @POST("api/orders")
    Call<AddOrder> addOrder(@Body AddOrder data);

    @POST("api/products")
    Call<List<Product>> searchPro(@Body BaseRequestSearch data);

//    @GET("api/products")
//    Call<Products> getProduct();


}
