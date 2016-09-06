package com.android.orlandosmits.contactapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView mUserListView;
    UserAdapter mUserAdapter;
    ArrayList mUserList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Person 1
        User u = new User();
        u.mFirstName = "Orlando";
        u.mLastName = "Smits";
        u.mGender = "Male";
        u.mEmail = "orlandosmits@live.nl";
        mUserList.add(u);

        //Create Person 2
        u = new User();
        u.mFirstName = "Tjebbe";
        u.mLastName = "Kerstens";
        u.mGender = "Male";
        u.mEmail = "tjkerstens@live.nl";
        mUserList.add(u);

        //Create Person 3
        u = new User();
        u.mFirstName = "Lucien";
        u.mLastName = "Ros";
        u.mGender = "Female";
        u.mEmail = "rosluuk@gmail.com";
        mUserList.add(u);

        //Create Person 4
        u = new User();
        u.mFirstName = "Paul";
        u.mLastName = "van den Burg";
        u.mGender = "Male";
        u.mEmail = "info@paulvandenburg.nl";
        mUserList.add(u);

        mUserListView = (ListView) findViewById(R.id.userListView);

        //Koppelen van de list
        mUserAdapter = new UserAdapter(this,
                getLayoutInflater(),
                mUserList);
        Log.i("log",mUserList.toString());
        mUserListView.setAdapter(mUserAdapter);

        mUserAdapter.notifyDataSetChanged();

        mUserListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("SelectedItem: ", position + "");
        Intent i = new Intent(getApplicationContext(), DetailActivity.class);
        User user = (User) this.mUserList.get(position);
        i.putExtra("UserName",  user.mFirstName);
        i.putExtra("UserLastName", user.mLastName);
        i.putExtra("UserEmail", user.mEmail);

        startActivity(i);

    }
}
