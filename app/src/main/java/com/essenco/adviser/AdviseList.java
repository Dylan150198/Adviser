package com.essenco.adviser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.essenco.adviser.Data.AsyncDAO;
import com.essenco.adviser.Domain.Advise;

import java.util.ArrayList;

public class AdviseList extends AppCompatActivity implements CallBackInterface {
    private static final String TAG = "AdviseList";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Bundle bundle = new Bundle();
    private String API_URL;
    private String adviseSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advise_list);
        this.recyclerView = findViewById(R.id.recyclerView);

        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(layoutManager);

        adapter = new RecycleViewAdapter(new ArrayList<Advise>(), this);
        recyclerView.setAdapter(adapter);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            API_URL = bundle.getString("URL");
            if (bundle.getString("Subject") != null) {
                adviseSubject = bundle.getString("Subject");
            } else {
                adviseSubject = "";
            }
        }
        AsyncDAO asyncDAO = new AsyncDAO(getApplicationContext(), this, API_URL, adviseSubject);
        asyncDAO.execute();
    }

    @Override
    public void adviseList(ArrayList<Advise> adviseArrayList) {
        try {
            if (!(adviseArrayList.size() == 0)) {
                adapter = new RecycleViewAdapter(adviseArrayList, this);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getApplicationContext(),
                        "No advise found about entered keywords.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
