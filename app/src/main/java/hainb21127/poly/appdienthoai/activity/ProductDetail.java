package hainb21127.poly.appdienthoai.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

public class ProductDetail extends AppCompatActivity {
    private Button btnDatHang;
    private ImageView imgBack,imgSp;
    private TextView tenSp,giaSp,motaSp,trangthaiSp;
    private Context context;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        tenSp = findViewById(R.id.tvTenSp_detailSp);
        giaSp = findViewById(R.id.tvGiaSp_detailSp);
        motaSp = findViewById(R.id.tvMotaSp_detailSp);
        trangthaiSp = findViewById(R.id.tvTrangthaiSp_detail);
        imgSp = findViewById(R.id.imgSp_detailSp);
        imgBack = findViewById(R.id.btnBack_detailSp);
        btnDatHang = findViewById(R.id.btnDatHang);
        context = this;

        Intent intent = getIntent();
        String idSp = intent.getStringExtra("idSp");
        String ten = intent.getStringExtra("ten");
        int gia = intent.getIntExtra("gia",0);
        String mota = intent.getStringExtra("mota");
        int tonkho = intent.getIntExtra("tonkho",0);
        String img = intent.getStringExtra("img");

        tenSp.setText(ten);
        giaSp.setText(Utilities.addDots(gia)+"đ");
        motaSp.setText(mota);
        if(tonkho > 0){
            trangthaiSp.setTextColor(Color.GREEN);
            trangthaiSp.setText("Còn hàng");
        }else{
            trangthaiSp.setTextColor(Color.RED);
            trangthaiSp.setText("Hết hàng");
        }
        Picasso.get().load(img).into(imgSp);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.checkLogin){
                    Intent intent = new Intent(ProductDetail.this,DatHangActivity.class);
                    intent.putExtra("idSp",idSp);
                    intent.putExtra("tenSp",ten);
                    intent.putExtra("giaSp",gia);
                    intent.putExtra("desc",mota);
                    intent.putExtra("tonkho",tonkho);
                    intent.putExtra("anhSp",img);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn phải đăng nhập để mua sản phẩm!");
                    builder.setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent it = new Intent(ProductDetail.this, SigninActivity.class);
                            startActivity(it);
                        }
                    });
                    builder.setNegativeButton("Cancel",null);
                    builder.show();
                }
            }
        });
    }
}