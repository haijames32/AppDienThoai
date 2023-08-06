package hainb21127.poly.appdienthoai.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.activity.DetailOrderActivity;
import hainb21127.poly.appdienthoai.config.Utilities;
import hainb21127.poly.appdienthoai.model.Order;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.MyViewHolder>{
    private List<Order> arrayList;
    private Context context;

    public AdapterOrder(Context context) {
        this.context = context;
    }

    public void setData(List<Order> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int i = position;
        Log.i("TAG", "onBindViewHolder: "+arrayList.size());
        Order order = arrayList.get(i);
        if(order == null){
            return;
        }
        holder.tensp.setText(order.getId_sanpham().getTensp());
        holder.soluong.setText("x"+order.getSoluong());
        holder.giasp.setText(Utilities.addDots(order.getId_sanpham().getGiasp())+"đ");
        holder.trangthai.setText(order.getTrangthai());
        Picasso.get().load(order.getId_sanpham().getImage()).into(holder.imgSp);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailOrderActivity.class);
                intent.putExtra("tensp",order.getId_sanpham().getTensp());
                intent.putExtra("giasp",order.getId_sanpham().getGiasp());
                intent.putExtra("trangthai",order.getTrangthai());
                intent.putExtra("anhsp",order.getId_sanpham().getImage());
                intent.putExtra("ngaymua",order.getNgaymua());
                intent.putExtra("soluong",order.getSoluong());
                intent.putExtra("tongtien",order.getTongtien());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(arrayList != null){
            return arrayList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgSp;
        private TextView tensp, giasp, soluong, trangthai;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSp = itemView.findViewById(R.id.imgSp_order);
            tensp = itemView.findViewById(R.id.tvtenSp_order);
            giasp = itemView.findViewById(R.id.tvGiaSp_order);
            soluong = itemView.findViewById(R.id.tvSoluong_order);
            trangthai = itemView.findViewById(R.id.tvTrangthai_order);
        }
    }
}
