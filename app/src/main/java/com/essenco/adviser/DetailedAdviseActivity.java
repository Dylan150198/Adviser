package com.essenco.adviser;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class DetailedAdviseActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextView txtAdvise;
    private TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_advise);
        this.txtAdvise = (TextView) findViewById(R.id.txtAdvise);
        if (getIntent().hasExtra("advise")) {
            Intent intent = getIntent();
            final String advise = intent.getStringExtra("advise");
            txtAdvise.setText(advise);
            textToSpeech = new TextToSpeech(this, this);
            textToSpeech.setLanguage(Locale.ENGLISH);
            findViewById(R.id.btnTts).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textToSpeech.speak(advise,
                            TextToSpeech.QUEUE_FLUSH, null);
                }
            });
        } else {
            txtAdvise.setText("Error");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textToSpeech.shutdown();}

    @Override
    public void onInit(int status) {

    }
}
