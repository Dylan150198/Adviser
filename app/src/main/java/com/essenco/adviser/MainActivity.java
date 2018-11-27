package com.essenco.adviser;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import com.essenco.adviser.Listeners.RandomAdviseListener;
import com.essenco.adviser.Listeners.SearchedAdviseListener;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Button btnAdvise;
    private Button btnRandomAdvise;
    private TextView txtAdvise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        btnAdvise = findViewById(R.id.btnAdvise);
        btnRandomAdvise = findViewById(R.id.btnRandomAdvise);
        txtAdvise = findViewById(R.id.txtAdvise);
        btnAdvise.setOnClickListener(new SearchedAdviseListener(this, txtAdvise));
        btnRandomAdvise.setOnClickListener(new RandomAdviseListener(this));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
