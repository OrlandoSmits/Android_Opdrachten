package com.android.orlandosmits.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView mPersonListView;
    ArrayAdapter mArrayAdapter;
    ArrayList mPersonList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person p = new Person();
        p.name = "Orlando Smits";
        p.age = 21;
        p.email = "orlandosmits@live.nl";
        mPersonList.add(p);
        p = new Person();
        p.name = "Orlando Smits";
        p.age = 21;
        p.email = "orlandosmits@live.nl";
        mPersonList.add(p);
        p = new Person();
        p.name = "Orlando Smits";
        p.age = 21;
        p.email = "orlandosmits@live.nl";
        mPersonList.add(p);

        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                mPersonList);
        mPersonListView.setAdapter(mArrayAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("SelectedItem: ", position + "");
    }
}
