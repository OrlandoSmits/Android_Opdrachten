package com.android.orlandosmits.seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        String value = extras.getString("VALUE");

        Log.i("", value);

        TextView tv = (TextView) findViewById(R.id.detailLabel);
        tv.setText("De waarde van de seekbar is:" + value);
    }
}
