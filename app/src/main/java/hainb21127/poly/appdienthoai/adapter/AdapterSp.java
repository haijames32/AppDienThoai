package hainb21127.poly.appdienthoai.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.activity.ProductDetail;
import hainb21127.poly.appdienthoai.config.Utilities;
import hainb21127.poly.appdienthoai.model.Product;

public class AdapterSp extends BaseAdapter {
    private Context context;
    private List<Product> arrayList;

    public AdapterSp(Context context) {
        this.context = context;
    }

    public void setData(List<Product> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(arrayList != null){
            return arrayList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = mInflater.inflate(R.layout.item_products,null);
            ImageView imgSp = view.findViewById(R.id.imgSp);
            TextView tensp = view.findViewById(R.id.tvTenSp);
            TextView giasp = view.findViewById(R.id.tvGiaSp);

            tensp.setText(arrayList.get(i).getTensp());
            giasp.setText(Utilities.addDots(arrayList.get(i).getGiasp())+"đ");
            Picasso.get().load(arrayList.get(i).getImage()).into(imgSp);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetail.class);
                    intent.putExtra("idSp",arrayList.get(i).get_id());
                    intent.putExtra("ten",arrayList.get(i).getTensp());
                    intent.putExtra("gia",arrayList.get(i).getGiasp());
                    intent.putExtra("mota",arrayList.get(i).getMota());
                    intent.putExtra("tonkho",arrayList.get(i).getTonkho());
                    intent.putExtra("img",arrayList.get(i).getImage());
                    context.startActivity(intent);
                }
            });
        }
        return view;
    }
}
