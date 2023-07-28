package hainb21127.poly.appdienthoai.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import hainb21127.poly.appdienthoai.inter.ServiceSignin;
import hainb21127.poly.appdienthoai.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    TextView tv;
    EditText edFullname,edUsername,edPasswd,edPhone,edEmail;
    Button btnRegister;
    Retrofit retrofit;
    ServiceSignin serviceSignin;
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
        btnRegister = findViewById(R.id.btnRegister);
        tv = findViewById(R.id.tvSignin);

        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL.POST_SIGN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceSignin = retrofit.create(ServiceSignin.class);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, SigninActivity.class);
                startActivity(intent);
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
                User user = new User();
                user.setFullname(full);
                user.setUsername(username);
                user.setEmail(email);
                user.setPhone(phone);
                user.setPasswd(passwd);
                Call<User> call = serviceSignin.registerUser(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            edUsername.setText("");
                            edFullname.setText("");
                            edEmail.setText("");
                            edPhone.setText("");
                            edPasswd.setText("");
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, SigninActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
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