package hainb21127.poly.appdienthoai.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.inter.RequestService;
import hainb21127.poly.appdienthoai.inter.RetrofitClient;
import hainb21127.poly.appdienthoai.model.BaseResponseSignUp;
import hainb21127.poly.appdienthoai.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    TextView tv;
    EditText edFullname,edUsername,edPasswd,edPhone,edEmail, edRePasswd;
    ImageView imgBack;
    Button btnRegister;
    Retrofit retrofit;
    private RequestService serviceRegister;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername = findViewById(R.id.edUsername);
        edFullname = findViewById(R.id.edFullname);
        edPhone = findViewById(R.id.edPhone);
        edEmail = findViewById(R.id.edEmail);
        edPasswd = findViewById(R.id.edPasswd);
        edRePasswd = findViewById(R.id.edRePasswd);
        btnRegister = findViewById(R.id.btnRegister);
        tv = findViewById(R.id.tvSignin);
        imgBack = findViewById(R.id.btnBack_register);

        serviceRegister = RetrofitClient.getService().create(RequestService.class);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full = edFullname.getText().toString();
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                int phone = Integer.parseInt(edPhone.getText().toString());
                String passwd = edPasswd.getText().toString();
                if(full.isEmpty() || username.isEmpty() || email.isEmpty() || Integer.valueOf(phone) == null || passwd.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!edRePasswd.getText().toString().equals(passwd)){
                    Toast.makeText(RegisterActivity.this, "Mật khẩu chưa trùng", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User();
                user.setFullname(full);
                user.setUsername(username);
                user.setEmail(email);
                user.setPhone(phone);
                user.setPasswd(passwd);
                Call<BaseResponseSignUp> call = serviceRegister.registerUser(user);
                call.enqueue(new Callback<BaseResponseSignUp>() {
                    @Override
                    public void onResponse(Call<BaseResponseSignUp> call, Response<BaseResponseSignUp> response) {
                        if(response.body().getStatus() == 200){
                            edUsername.setText("");
                            edFullname.setText("");
                            edEmail.setText("");
                            edPhone.setText("");
                            edPasswd.setText("");
                            Toast.makeText(RegisterActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            onBackPressed();
//                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponseSignUp> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    }
                });
//                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                String username = edUsername.getText().toString();
//                String full = edFullname.getText().toString();
//                String phone = edPhone.getText().toString();
//                String email = edEmail.getText().toString();
//                String pw = edPasswd.getText().toString();
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("username",username);
//                    object.put("fullname",full);
//                    object.put("phone",phone);
//                    object.put("email",email);
//                    object.put("pw",pw);
//                    JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, API_URL.POST_SIGN.concat("register"), object, new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                String msg = response.getString("msg");
//                                boolean sc = response.getBoolean("success");
//                                if(sc == true){
//                                    edUsername.setText("");
//                                    edFullname.setText("");
//                                    edEmail.setText("");
//                                    edPhone.setText("");
//                                    edPasswd.setText("");
//                                    Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(RegisterActivity.this, SigninActivity.class);
//                                    startActivity(intent);
//                                }else{
//                                    Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
//                                }
//                            }catch (Exception ex){
//                                ex.printStackTrace();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(RegisterActivity.this, "Lỗi "+error.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    requestQueue.add(objectRequest);
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
            }
        });
    }
}