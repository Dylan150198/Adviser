package com.essenco.adviser;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.essenco.adviser.Domain.Advise;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private static final String TAG = "RecycleViewAdapter";
    private ArrayList<Advise> advises;
    private Context context;

    public RecycleViewAdapter(ArrayList<Advise> advises, Context context) {
        this.advises = advises;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_listitem, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final Advise advise = advises.get(i);
        viewHolder.txtAdvise.setText(advise.getAdvise());
        viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Advise clickedAdvise = advises.get(i);
                Intent intent = new Intent(v.getContext(), DetailedAdviseActivity.class);
                intent.putExtra("advise",  clickedAdvise.getAdvise());
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return advises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtAdvise;
        public ConstraintLayout parent_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtAdvise = itemView.findViewById(R.id.txtAdvise);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
