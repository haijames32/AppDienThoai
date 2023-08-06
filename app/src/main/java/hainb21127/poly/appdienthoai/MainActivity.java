package hainb21127.poly.appdienthoai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hainb21127.poly.appdienthoai.fragment.HomeFragment;
import hainb21127.poly.appdienthoai.fragment.OrderFragment;
import hainb21127.poly.appdienthoai.fragment.ProfileFragment;
import hainb21127.poly.appdienthoai.activity.SigninActivity;

public class MainActivity extends AppCompatActivity {
//    ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;
    Fragment fragment;
    public static boolean checkLogin = false;
    public static String frm = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set Tab home làm màn hình chính
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        replaceFragment(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_home ){
                    // Khi chọn Home
                    fragment = new HomeFragment();
                    replaceFragment(fragment);
                    return true;
//                    if(fragment instanceof HomeFragment){
//                        return true;
//                    }else{
//                        fragment = new HomeFragment();
//                        replaceFragment(fragment);
//                        return true;
//                    }
                }
                if(item.getItemId() == R.id.nav_order ){
                    // Khi chọn Order
                    if(checkLogin){
                        fragment = new OrderFragment();
                        replaceFragment(fragment);
                        return true;
                    }else{
                        Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                        startActivity(intent);
                    }

                }
                if(item.getItemId() == R.id.nav_profile ){
                    // Khi chọn Profile
                    if(checkLogin){
                        fragment = new ProfileFragment();
                        replaceFragment(fragment);
                        return true;
                    }else{
                        Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                        startActivity(intent);
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

    @Override
    protected void onResume() {
        super.onResume();
        if (frm.equals("Đơn mua")) {
            replaceFragment(new OrderFragment());
            bottomNavigationView.setSelectedItemId(R.id.nav_order);
            frm = "";
        }
    }
}