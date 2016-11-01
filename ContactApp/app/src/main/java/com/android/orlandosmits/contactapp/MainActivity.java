package com.android.orlandosmits.contactapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView mUserListView;
    UserAdapter mUserAdapter;
    DatabaseHandler dbHandler;
    ArrayList<User> mUserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        dbHandler = new DatabaseHandler(this);
        mUserList = dbHandler.getUsers();

        if(mUserList.size() <= 0) {
            for (int i = 0; i < 5; i++){
                GETRequest();
            }
        }

        Log.i("userlist", mUserList.toString());
        mUserListView = (ListView) findViewById(R.id.userListView);

        //Koppelen van de list
        mUserAdapter = new UserAdapter(this,
                getLayoutInflater(),
                mUserList);
        Log.i("log",mUserList.toString());
        mUserListView.setAdapter(mUserAdapter);

        mUserAdapter.notifyDataSetChanged();

        mUserListView.setOnItemClickListener(this);
        mUserListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) mUserList.get(position);
                CharSequence text = user.getmFirstName() + " verwijderd";
                int duration = Toast.LENGTH_LONG;
                Context context = getApplicationContext();

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                mUserList.remove(position);
                dbHandler.removeUser(user);
                mUserAdapter.notifyDataSetChanged();
                return false;

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_deleteDB:
                dbHandler.removeDb();
                mUserList.clear();
                mUserAdapter.notifyDataSetChanged();

                return true;

            case R.id.action_addPerson:
                GETRequest();
                mUserAdapter.notifyDataSetChanged();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("SelectedItem: ", position + "");
        Intent i = new Intent(getApplicationContext(), DetailActivity.class);
        User user = (User) this.mUserList.get(position);
        i.putExtra("User", user);

        startActivity(i);
    }


    private void getJSON(String response)
    {
        String firstName = "Orlando";
        String lastName = "Smits";
        String image = "Imagestring";
        String gender = "Male";
        String email = "orlandosmits@live.nl";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject results = jsonObject.getJSONArray("results").getJSONObject(0);

            JSONObject name = results.getJSONObject("name");
            firstName = name.get("first").toString();
            lastName = name.get("last").toString();

            JSONObject thumbnail = results.getJSONObject("picture");
            image = thumbnail.get("large").toString();
            gender = results.get("gender").toString();
            email = results.get("email").toString();

            Log.i("Return Data", firstName + lastName + image + gender + email);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        User u = new User();
        u.mFirstName = firstName;
        u.mLastName = lastName;
        u.mGender = gender;
        u.mEmail = email;
        u.mImage = image;

        dbHandler.addUser(u);
        mUserList.add(u);
        mUserAdapter.notifyDataSetChanged();
    }

    public void GETRequest() {
        String url = "https://api.randomuser.me";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        getJSON(response);
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getRandomUser", error.toString());
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
