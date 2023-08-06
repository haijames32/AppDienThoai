package hainb21127.poly.appdienthoai.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.adapter.AdapterSp;
import hainb21127.poly.appdienthoai.inter.RequestService;
import hainb21127.poly.appdienthoai.inter.RetrofitClient;
import hainb21127.poly.appdienthoai.model.BaseRequestSearch;
import hainb21127.poly.appdienthoai.model.Product;
import hainb21127.poly.appdienthoai.model.Theloai;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private Spinner spnCat;
//    private RecyclerView rcv;
    private GridView grv_sp;
    private List<String> listCatName;
    private List<Theloai> listCat;
    private List<Product> listProd;
    private RequestService serviceSp;
    private Context context;
    private String idCat;
    private AdapterSp adapterSp;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EditText edSearch;
    private ImageButton btnSearch;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        grv_sp = view.findViewById(R.id.grv_sp);
        spnCat = view.findViewById(R.id.spnCat);
        edSearch = view.findViewById(R.id.edSearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        listCat = new ArrayList<>();
        listCatName = new ArrayList<>();
        adapterSp = new AdapterSp(getContext());
        context = view.getContext();
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout2);

        serviceSp = RetrofitClient.getService().create(RequestService.class);

        loadData();

        loadRcv(idCat);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = edSearch.getText().toString();
                BaseRequestSearch baseRequestSearch = new BaseRequestSearch();
                baseRequestSearch.setSearchpro(search);
                Call<List<Product>> call = serviceSp.searchPro(baseRequestSearch);
                call.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getContext(),"Đã search"+response.body(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Toast.makeText(getContext(), "Lỗi search", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    idCat = listCat.get(i).get_id();
                    loadRcv(idCat);
                }catch (Exception e){
                    Log.i("TAG", "onItemSelected: "+e);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void loadRcv(String idCat) {
        Call<List<Product>> call = serviceSp.getProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.code() == 200) {
                    listProd = response.body();
                    List<Product> listSortCat = new ArrayList<>();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        listProd.forEach(Product -> {
                            if(Product.getId_theloai().get_id().equals(idCat)){
                                listSortCat.add(Product);
                            }
                        });
                    }
                    adapterSp.setData(listSortCat);
                    grv_sp.setAdapter(adapterSp);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void loadData(){
        Call<List<Theloai>> call = serviceSp.getCategory();
        call.enqueue(new Callback<List<Theloai>>() {
            @Override
            public void onResponse(Call<List<Theloai>> call, Response<List<Theloai>> response) {
                if (response.code() == 200) {
                    listCat = response.body();
                    idCat = listCat.get(0).get_id();
                    for (Theloai cat : listCat) {
                        listCatName.add(cat.getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listCatName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnCat.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Theloai>> call, Throwable t) {

            }
        });
    }
}