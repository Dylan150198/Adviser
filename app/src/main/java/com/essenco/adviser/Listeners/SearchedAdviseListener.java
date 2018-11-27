package com.essenco.adviser.Listeners;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.essenco.adviser.AdviseList;
import com.essenco.adviser.Constants;

public class SearchedAdviseListener implements View.OnClickListener{
    private Context context;
    private TextView txtAdvise;
    private String adviseSubject;
    public SearchedAdviseListener(Context context, TextView textView) {
        this.context = context;
        this.txtAdvise = textView;
    }
    @Override
    public void onClick(View v) {
        adviseSubject = txtAdvise.getText().toString();
        Intent intent = new Intent(context, AdviseList.class);
        intent.putExtra("URL", Constants.URL_AdviseBySearch + adviseSubject);
        intent.putExtra("Subject", adviseSubject);
        context.startActivity(intent);
    }
}
