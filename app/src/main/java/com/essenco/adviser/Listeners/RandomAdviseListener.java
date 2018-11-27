package com.essenco.adviser.Listeners;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.essenco.adviser.AdviseList;
import com.essenco.adviser.Constants;

public class RandomAdviseListener implements View.OnClickListener {
    private Context context;
    public RandomAdviseListener(Context context) {
        this.context = context;
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, AdviseList.class);
        intent.putExtra("URL", Constants.URL_AdviseRandom);
        context.startActivity(intent);
    }

}
