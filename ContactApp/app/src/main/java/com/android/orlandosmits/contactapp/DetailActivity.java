package com.android.orlandosmits.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       Bundle extras = getIntent().getExtras();
        User user = (User) extras.getSerializable("User");

        String uName = user.getFullName();
        String uEmail = user.getmEmail();
        String uGender = user.getmGender();
        String uImage = user.getmImage();


        TextView tUname = (TextView) findViewById(R.id.userName);
        tUname.setText(uName);

        TextView tUemail = (TextView) findViewById(R.id.userEmail);
        tUemail.setText(uEmail);

        TextView tUgender = (TextView) findViewById(R.id.userGender);
        tUgender.setText(uGender);

        ImageView thumbnail = (ImageView) findViewById(R.id.userImage);
        Picasso.with(getApplicationContext()).load(uImage).into(thumbnail);

    }
}
