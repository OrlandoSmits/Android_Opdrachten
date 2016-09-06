package com.android.orlandosmits.seekbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar);

        Button button = (Button) findViewById(R.id.btnValue);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("onProgressChagned", seekBar.toString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i("onStartTrackingTouch", seekBar.toString());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("onStopTrackingTouch", seekBar.toString());
                int value = seekBar.getProgress();
                Toast toast = Toast.makeText(MainActivity.this, String.valueOf(value), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i("Button click", "");

                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                int value = seekbar.getProgress();
                i.putExtra("VALUE", String.valueOf(value));

                startActivity(i);
            }
        });
    }
}
