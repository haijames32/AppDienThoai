package hainb21127.poly.appdienthoai.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import hainb21127.poly.appdienthoai.MainActivity;
import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.config.Utilities;
import hainb21127.poly.appdienthoai.inter.RequestService;
import hainb21127.poly.appdienthoai.inter.RetrofitClient;
import hainb21127.poly.appdienthoai.model.AddOrder;
import hainb21127.poly.appdienthoai.model.Order;
import hainb21127.poly.appdienthoai.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatHangActivity extends AppCompatActivity {
    private TextView tvFullname, tvPhone, tvEmail, tvTenSp, tvGiaSp, tvGiaSp2, tvSoluong, tvSoluong2, tvTongtien, tvTongtien2;
    private ImageView imgSp, btnGiamsl, btnTangsl, imgBack;
    private Button btnDatHang;
    private RequestService service;
    private int so = 1;
    private int tong = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang);
        tvFullname = findViewById(R.id.tvFullname_dathang);
        tvPhone = findViewById(R.id.tvPhone_dathang);
        tvEmail = findViewById(R.id.tvEmail_dathang);
        tvTenSp = findViewById(R.id.tvTenSp_dathang);
        tvGiaSp = findViewById(R.id.tvGiaSp_dathang);
        tvGiaSp2 = findViewById(R.id.tvGiaSp_tt);
        tvSoluong = findViewById(R.id.tvSoluong_dathang);
        tvSoluong2 = findViewById(R.id.tvSoluong_tt);
        tvTongtien = findViewById(R.id.tvTongtien_tt);
        tvTongtien2 = findViewById(R.id.tvTongtien_tt2);
        imgSp = findViewById(R.id.imgSp_dathang);
        btnDatHang = findViewById(R.id.btnDatHang_tt);
        btnGiamsl = findViewById(R.id.btnGiamsl);
        btnTangsl = findViewById(R.id.btnTangsl);
        imgBack = findViewById(R.id.btnBack_tt);

        service = RetrofitClient.getService().create(RequestService.class);

        // Lấy thông tin người dùng
        SharedPreferences sharedPreferences = getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        String full = sharedPreferences.getString("fullname","");
        String email = sharedPreferences.getString("email","");
        int phone = sharedPreferences.getInt("phone",0);

        // Lấy thông tin sản phẩm
        Intent intent = getIntent();
        String tensp = intent.getStringExtra("tenSp");
        int giasp = intent.getIntExtra("giaSp",0);
        String img = intent.getStringExtra("anhSp");

        tvFullname.setText(full);
        tvPhone.setText("0"+phone);
        tvEmail.setText(email);

        tvTenSp.setText(tensp);
        tvSoluong.setText(so+"");
        tvSoluong2.setText(so+"");
        tvGiaSp.setText(Utilities.addDots(giasp)+"đ");
        tvTongtien.setText(Utilities.addDots(giasp * so)+"đ");
        tvTongtien2.setText(Utilities.addDots(giasp * so)+"đ");
        Picasso.get().load(img).into(imgSp);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnGiamsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(so > 1){
                    so--;
                    tvSoluong.setText(String.valueOf(so));
                    tvSoluong2.setText(String.valueOf(so));
                    tong = giasp * so;
                    tvTongtien.setText(Utilities.addDots(Integer.parseInt(String.valueOf(tong)))+"đ");
                    tvTongtien2.setText(Utilities.addDots(Integer.parseInt(String.valueOf(tong)))+"đ");
                }
            }
        });

        btnTangsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                so++;
                tvSoluong.setText(String.valueOf(so));
                tvSoluong2.setText(String.valueOf(so));
                tong = giasp * so;
                tvTongtien.setText(Utilities.addDots(Integer.parseInt(String.valueOf(tong)))+"đ");
                tvTongtien2.setText(Utilities.addDots(Integer.parseInt(String.valueOf(tong)))+"đ");
            }
        });


        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idU = sharedPreferences.getString("idUser","");
                String idSp = intent.getStringExtra("idSp");
                AddOrder order = new AddOrder();
                order.setId_khachhang(idU);
                order.setId_sanpham(idSp);
                order.setSoluong(so);
                Call<AddOrder> call = service.addOrder(order);
                call.enqueue(new Callback<AddOrder>() {
                    @Override
                    public void onResponse(Call<AddOrder> call, Response<AddOrder> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent(DatHangActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(DatHangActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddOrder> call, Throwable t) {
                        Toast.makeText(DatHangActivity.this, "Đặt hàng không thành công: "+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}