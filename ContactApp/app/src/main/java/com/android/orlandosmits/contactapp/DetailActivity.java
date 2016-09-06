package com.android.orlandosmits.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       Bundle extras = getIntent().getExtras();
        String username = extras.getString("UserName");
        String lastname = extras.getString("UserLastName");
        String email = extras.getString("UserEmail");

        TextView tvName = (TextView) findViewById(R.id.tvPersonName);
        tvName.setText(username + " " + lastname);

        TextView tvEmail = (TextView) findViewById(R.id.tvPersonEmail);
        tvEmail.setText(email);

        Button btnTerug = (Button) findViewById(R.id.btnTerugMenu);
        btnTerug.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i("Terug", "");

                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(i);
            }
        });

    }
}
