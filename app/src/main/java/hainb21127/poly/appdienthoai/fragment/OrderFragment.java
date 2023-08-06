package hainb21127.poly.appdienthoai.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.adapter.AdapterOrder;
import hainb21127.poly.appdienthoai.inter.RequestService;
import hainb21127.poly.appdienthoai.inter.RetrofitClient;
import hainb21127.poly.appdienthoai.model.Order;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderFragment extends Fragment {
    private Retrofit retrofit;
    private AdapterOrder adapterOrder;
    private List<Order> arrayList;
    private RequestService serviceOrder;
    private Context context;
    private RecyclerView rcv_order;
    private LinearLayout no_order;
    public OrderFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_order = view.findViewById(R.id.rcv_order);
        no_order = view.findViewById(R.id.no_order);

        context = view.getContext();
        adapterOrder = new AdapterOrder(getContext());

        arrayList = new ArrayList<>();
        serviceOrder = RetrofitClient.getService().create(RequestService.class);

        loadData();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        String idU = sharedPreferences.getString("idUser","");
        Log.e("zzzzz", "user: "+idU);
        Call<List<Order>> call = serviceOrder.getOrder(idU);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.code() == 200) {
                    arrayList = response.body();
                    adapterOrder.setData(arrayList);
                    LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
                    rcv_order.setLayoutManager(manager);
                    rcv_order.setAdapter(adapterOrder);
                }
                if(arrayList.size() == 0){
                    no_order.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.i("TAG", "onFailure: "+t);
            }
        });
    }

}