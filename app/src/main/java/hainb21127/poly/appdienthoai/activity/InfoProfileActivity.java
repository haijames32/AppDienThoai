package hainb21127.poly.appdienthoai.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.inter.RequestService;
import hainb21127.poly.appdienthoai.inter.RetrofitClient;
import hainb21127.poly.appdienthoai.model.Order;
import hainb21127.poly.appdienthoai.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoProfileActivity extends AppCompatActivity {
    private EditText edFullname, edPhone, edEmail;
    private Button btnCancel, btnSave;
    private ImageView imgBack;
    private RequestService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_profile);
        edFullname = findViewById(R.id.edFullname_edit);
        edPhone = findViewById(R.id.edPhone_edit);
        edEmail = findViewById(R.id.edEmail_edit);
        btnCancel = findViewById(R.id.btnCancel_edit);
        btnSave = findViewById(R.id.btnSave_edit);
        imgBack = findViewById(R.id.btnBack_edit);

        service = RetrofitClient.getService().create(RequestService.class);

        Intent intent = getIntent();
        String idU = intent.getStringExtra("idU");
        String full = intent.getStringExtra("fullname");
        String email = intent.getStringExtra("email");
        int phone = intent.getIntExtra("phone",0);

        edFullname.setText(full);
        edEmail.setText(email);
        edPhone.setText("0"+phone);

        btnCancel.setOnClickListener(new View.OnClickListener() {
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full = edFullname.getText().toString();
                String email = edEmail.getText().toString();
                int phone = Integer.parseInt(edPhone.getText().toString());
                Call<User> call = service.editUser(idU,new User(full,email,phone));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            SharedPreferences sharedPreferences = getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("fullname",full);
                            editor.putString("email",email);
                            editor.putInt("phone",phone);
                            editor.apply();
                            edFullname.setText("");
                            edEmail.setText("");
                            edPhone.setText("");
                            onBackPressed();
                            Toast.makeText(InfoProfileActivity.this, "Thay đổi thông tin thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(InfoProfileActivity.this, "Thay đổi không thành công"+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}