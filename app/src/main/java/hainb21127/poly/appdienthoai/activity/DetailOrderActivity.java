package hainb21127.poly.appdienthoai.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.config.Utilities;

public class DetailOrderActivity extends AppCompatActivity {
    private TextView tvFullname, tvPhone, tvEmail, tvNgaymua, tvTenSp, tvGiaSp, tvSoluong, tvGiaSp2, tvSoluong2, tvTongtien, tvTrangthai;
    private ImageView imgSp, btnBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        tvFullname = findViewById(R.id.tvFullname_orderdetail);
        tvPhone = findViewById(R.id.tvPhone_orderdetail);
        tvEmail = findViewById(R.id.tvEmail_orderdetail);
        tvNgaymua = findViewById(R.id.tvNgaymua_orderdetail);
        tvTenSp = findViewById(R.id.tvTenSp_orderdetail);
        tvGiaSp = findViewById(R.id.tvGiaSp_orderdetail);
        tvSoluong = findViewById(R.id.tvSoluong_orderdetail);
        tvGiaSp2 = findViewById(R.id.tvGiaSp2_orderdetail);
        tvSoluong2 = findViewById(R.id.tvSoluong2_orderdetail);
        tvTongtien = findViewById(R.id.tvTongtien_orderdetail);
        tvTrangthai = findViewById(R.id.tvTrangthai_orderdetail);
        imgSp = findViewById(R.id.imgSp_orderdetail);
        btnBack = findViewById(R.id.btnBack_orderdetail);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        setData();
    }

    private void setData(){
        // Lấy thông tin người dùng
        SharedPreferences sharedPreferences = getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        String full = sharedPreferences.getString("fullname","");
        String email = sharedPreferences.getString("email","");
        int phone = sharedPreferences.getInt("phone",0);

        // Lấy thông tin sản phẩm
        Intent intent = getIntent();
        String tensp = intent.getStringExtra("tensp");
        int giasp = intent.getIntExtra("giasp",0);
        String img = intent.getStringExtra("anhsp");
        String trangthai = intent.getStringExtra("trangthai");
        int soluong = intent.getIntExtra("soluong",0);
        String ngaymua = intent.getStringExtra("ngaymua");
        int tongtien = intent.getIntExtra("tongtien",0);


        tvFullname.setText(full);
        tvPhone.setText("0"+phone);
        tvEmail.setText(email);

        tvNgaymua.setText(ngaymua);
        tvTenSp.setText(tensp);
        tvGiaSp.setText(Utilities.addDots(giasp)+"đ");
        tvSoluong.setText("x"+soluong);
        tvGiaSp2.setText(Utilities.addDots(giasp)+"đ");
        tvSoluong2.setText("x"+soluong);
        tvTongtien.setText(Utilities.addDots(tongtien)+"đ");
        if(trangthai.equals("Đã hoàn thành")){
            tvTrangthai.setTextColor(Color.GREEN);
        }else{
            tvTrangthai.setTextColor(Color.BLUE);
        }
        tvTrangthai.setText(trangthai);
        Picasso.get().load(img).into(imgSp);
    }
}