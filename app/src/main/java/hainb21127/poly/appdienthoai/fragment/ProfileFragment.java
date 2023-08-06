package hainb21127.poly.appdienthoai.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import hainb21127.poly.appdienthoai.MainActivity;
import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.activity.InfoProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    private TextView tvFullname,tvPhone,tvEmail;
    private LinearLayout btnLogout, btnInfo, btnOrder;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvFullname = view.findViewById(R.id.tvFullname);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvEmail = view.findViewById(R.id.tvEmail);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnInfo = view.findViewById(R.id.btnInfo);
        btnOrder = view.findViewById(R.id.btnOrder);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        String idU = sharedPreferences.getString("idUser","");
        String full = sharedPreferences.getString("fullname","");
        int phone = sharedPreferences.getInt("phone",0);
        String email = sharedPreferences.getString("email","");
        tvFullname.setText(full);
        tvPhone.setText("0"+phone);
        tvEmail.setText(email);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String full = sharedPreferences.getString("fullname","");
                int phone = sharedPreferences.getInt("phone",0);
                String email = sharedPreferences.getString("email","");
                tvFullname.setText(full);
                tvPhone.setText("0"+phone);
                tvEmail.setText(email);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InfoProfileActivity.class);
                intent.putExtra("idU",idU);
                intent.putExtra("fullname",full);
                intent.putExtra("phone",phone);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.frm = "Đơn mua";
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn đăng xuất?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences.edit().clear().commit();
                        MainActivity.checkLogin = false;
                        Intent intent = new Intent(getContext(),MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("NO",null);
                builder.show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!MainActivity.checkLogin){
            Intent intent = new Intent(getContext(),MainActivity.class);
            startActivity(intent);
        }
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
//        String full = sharedPreferences.getString("fullname","");
//        int phone = sharedPreferences.getInt("phone",0);
//        String email = sharedPreferences.getString("email","");
//        tvFullname.setText(full);
//        tvPhone.setText("0"+phone);
//        tvEmail.setText(email);
    }
}