package hainb21127.poly.appdienthoai.activity;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import hainb21127.poly.appdienthoai.API_URL;
import hainb21127.poly.appdienthoai.MainActivity;
import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.inter.RequestService;
import hainb21127.poly.appdienthoai.inter.RetrofitClient;
import hainb21127.poly.appdienthoai.model.BaseRequestSignin;
import hainb21127.poly.appdienthoai.model.BaseResponseSignin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SigninActivity extends AppCompatActivity {
    private TextView tv;
    private EditText edUsername,edPasswd;
    private ImageView imgBack;
    private Button btnLogin;
    private RequestService service;

    public void saveSharedPref(String idUser, String fullname, String username, String passwd, String email, int phone){
        SharedPreferences sharedPreferences = getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idUser", idUser);
        editor.putString("fullname", fullname);
        editor.putString("username", username);
        editor.putString("passwd", passwd);
        editor.putString("email", email);
        editor.putInt("phone", phone);
        editor.apply();
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
        imgBack = findViewById(R.id.btnBack_signin);

        service = RetrofitClient.getService().create(RequestService.class);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                String username = edUsername.getText().toString();
//                String passwd = edPasswd.getText().toString();
//                if(username.isEmpty() || passwd.isEmpty()){
//                    Toast.makeText(SigninActivity.this, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
//                }
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("username",username);
//                    object.put("passwd",passwd);
//                    JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, API_URL.API_URL.concat("api/signin"), object, new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                String msg = response.getString("msg");
//                                boolean sc = response.getBoolean("success");
//                                if(sc){
//                                    JSONObject jsonObject = response.getJSONObject("obj");
//                                    String idU = jsonObject.getString("_id");
//                                    String full = jsonObject.getString("fullname");
//                                    String username = jsonObject.getString("username");
//                                    String pw = jsonObject.getString("passwd");
//                                    String email = jsonObject.getString("email");
//                                    int phone = jsonObject.getInt("phone");
//                                    saveSharedPref(idU, full, username, pw, email, phone);
//                                    MainActivity.checkLogin = true;
////                                    Intent intent = new Intent(SigninActivity.this, MainActivity.class);
////                                    startActivity(intent);
//                                    finish();
//                                    Toast.makeText(SigninActivity.this, msg, Toast.LENGTH_SHORT).show();
//                                    Log.e("login", "Login: "+ response.toString() );
//                                }else{
//                                    Toast.makeText(SigninActivity.this, msg, Toast.LENGTH_SHORT).show();
//                                }
//                            }catch (Exception ex){
//                                ex.printStackTrace();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(SigninActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    requestQueue.add(objectRequest);
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
                String username = edUsername.getText().toString();
                String passwd = edPasswd.getText().toString();
                if(username.isEmpty() || passwd.isEmpty()){
                    Toast.makeText(SigninActivity.this, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                BaseRequestSignin requestSignin = new BaseRequestSignin();
                requestSignin.setUsername(username);
                requestSignin.setPasswd(passwd);
                Call<BaseResponseSignin> call = service.signinUser(requestSignin);
                call.enqueue(new Callback<BaseResponseSignin>() {
                    @Override
                    public void onResponse(Call<BaseResponseSignin> call, Response<BaseResponseSignin> response) {
                        if(response.body().getStatus() == 200){
                            String idU = response.body().getObj().get_id();
                            String fullname = response.body().getObj().getFullname();
                            String username = response.body().getObj().getUsername();
                            String passwd = response.body().getObj().getPasswd();
                            String email = response.body().getObj().getEmail();
                            int phone = response.body().getObj().getPhone();
                            saveSharedPref(idU,fullname,username,passwd,email,phone);
                            MainActivity.checkLogin = true;
                            finish();
                            Toast.makeText(SigninActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SigninActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponseSignin> call, Throwable t) {
                        Toast.makeText(SigninActivity.this, "Đăng nhập không thành công"+t, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

}