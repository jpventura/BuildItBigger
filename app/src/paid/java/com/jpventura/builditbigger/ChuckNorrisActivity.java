package com.jpventura.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ChuckNorrisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuck_norris);

        String fact = getIntent().getStringExtra("fact");
        TextView mTextView = (TextView) findViewById(R.id.text_fact);
        mTextView.setText(fact);
    }
}
