package hainb21127.poly.appdienthoai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import hainb21127.poly.appdienthoai.databinding.ActivityMainBinding;
import hainb21127.poly.appdienthoai.R;
import hainb21127.poly.appdienthoai.fragment.CategoryFragment;
import hainb21127.poly.appdienthoai.fragment.HomeFragment;
import hainb21127.poly.appdienthoai.fragment.OrderFragment;
import hainb21127.poly.appdienthoai.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {
//    ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        replaceFragment(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_home ){
                    // Khi chọn Home
                    if(fragment instanceof HomeFragment){
                        return true;
                    }else{
                        fragment = new HomeFragment();
                        replaceFragment(fragment);
                        return true;
                    }
                }
                if(item.getItemId() == R.id.nav_category ){
                    // Khi chọn Category
                    if(fragment instanceof CategoryFragment){
                        return true;
                    }else{
                        fragment = new CategoryFragment();
                        replaceFragment(fragment);
                        return true;
                    }
                }
                if(item.getItemId() == R.id.nav_order ){
                    // Khi chọn Order
                    if(fragment instanceof OrderFragment){
                        return true;
                    }else{
                        fragment = new OrderFragment();
                        replaceFragment(fragment);
                        return true;
                    }
                }
                if(item.getItemId() == R.id.nav_profile ){
                    // Khi chọn Profile
                    if(fragment instanceof ProfileFragment){
                        return true;
                    }else{
                        fragment = new ProfileFragment();
                        replaceFragment(fragment);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout,fragment);
//        fragmentTransaction.commit();
    }
}