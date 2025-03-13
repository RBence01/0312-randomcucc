package com.example.miertkelleztafeladatotcsinalni;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private NavigationView nav;
    private DrawerLayout drawer;
    private static APIService api;
    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        instance = this;

        api = getApi();
        nav = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(false)).commit();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                navigateTo(id);

                drawer.close();
                return true;
            }
        });
    }

    public static APIService getApi() {
        if (api == null) api = APIInstance.getInstance().create(APIService.class);
        return api;
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public void navigateTo(int id) {
        Fragment fragment = null;
        if (id == R.id.nav_listing) fragment = new HomeFragment(false);
        else if (id == R.id.nav_create) fragment = new CreateFragment();
        else if (id == R.id.nav_modify) fragment = new HomeFragment(true);

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }

    public void navigateTo(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }
}