package hainb21127.poly.appdienthoai.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

import hainb21127.poly.appdienthoai.API_URL;
import hainb21127.poly.appdienthoai.MainActivity;
import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.inter.ServiceSignin;
import hainb21127.poly.appdienthoai.model.UserLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SigninActivity extends AppCompatActivity {
    TextView tv;
    EditText edUsername,edPasswd;
    Button btnLogin;
    ServiceSignin serviceSignin;
    Retrofit retrofit;

    public void saveSharedPref(String idUser, String fullname, String username, String passwd, String email, int phone, boolean checkU){
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idUser", idUser);
        editor.putString("fullname", fullname);
        editor.putString("username", username);
        editor.putString("passwd", passwd);
        editor.putString("email", email);
        editor.putInt("phone", phone);
        editor.putBoolean("checkU", checkU);
        editor.commit();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edUsername = findViewById(R.id.ed_username);
        edPasswd = findViewById(R.id.ed_passwd);
        btnLogin = findViewById(R.id.btnLogin);
        tv = findViewById(R.id.tvRegister);

//        retrofit = new Retrofit.Builder()
//                .baseUrl(API_URL.POST_SIGN)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        serviceSignin = retrofit.create(ServiceSignin.class);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String username = edUsername.getText().toString();
//                String passwd = edPasswd.getText().toString();
//                Call<UserLogin> userLoginCall = serviceSignin.postSignin(username,passwd);
//                userLoginCall.enqueue(new Callback<UserLogin>() {
//                    @Override
//                    public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
//                        if(response.isSuccessful()){
//                            Intent i =new Intent(SigninActivity.this,MainActivity.class);
//                            startActivity(i);
//                            UserLogin userLogin = response.body();
//                            Log.i("login", userLogin.getUsername());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserLogin> call, Throwable t) {
//                        Toast.makeText(SigninActivity.this, "Không đăng nhập được", Toast.LENGTH_SHORT).show();
//                    }
//                });

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String username = edUsername.getText().toString();
                String passwd = edPasswd.getText().toString();
                try {
                    JSONObject object = new JSONObject();
                    object.put("username",username);
                    object.put("passwd",passwd);
                    JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, API_URL.POST_SIGN.concat("api/signin"), object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String msg = response.getString("msg");
                                boolean sc = response.getBoolean("success");
                                if(sc == true){
                                    JSONObject jsonObject = response.getJSONObject("obj");
                                    String idU = jsonObject.getString("_id");
                                    String full = jsonObject.getString("fullname");
                                    String username = jsonObject.getString("username");
                                    String pw = jsonObject.getString("passwd");
                                    String email = jsonObject.getString("email");
                                    int phone = jsonObject.getInt("phone");
                                    saveSharedPref(idU, full, username, pw, email, phone, true);
                                    Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(SigninActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(SigninActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SigninActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(objectRequest);
                }catch (Exception ex){
                    ex.printStackTrace();
                }


            }
        });
    }

//    private void getData(List<UserLogin> list){
//        for(UserLogin people:list){
//            Log.i("====people====",people.toString());
//        }
//    }

//    private void login(String username, String password) {
//        serviceSignin = ServiceRetrofit.getServiceSignin();
//        serviceSignin.login(username, password)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResponseData>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResponseData responseData) {
//                        // Xử lý phản hồi từ API
//                        if (responseData.isSuccess()) {
//                            // Đăng nhập thành công
//                            Toast.makeText(SigninActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//
//                            // Lưu thông tin người dùng vào SharedPreferences
//                            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString("username", username);
//                            editor.putString("password", password);
//                            editor.apply();
//
//                            // Chuyển đến màn hình chính
//                            startActivity(new Intent(SigninActivity.this, MainActivity.class));
//                            finish();
//                        } else {
//                            // Đăng nhập thất bại
//                            Toast.makeText(SigninActivity.this, responseData.getMsg(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        // Xảy ra lỗi trong quá trình xử lý
//                        Toast.makeText(SigninActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
}