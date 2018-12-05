package com.example.alextseng.s;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;
import android.widget.Switch;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);


        Button btn1 = (Button) findViewById(R.id.generatebutton);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Log.e("My button" , "This is log message!");
            }
        } );

        //big dick energy
        Switch swtch1 = (Switch) findViewById(R.id.switch1);
        swtch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.e("My switch" , "This is log message!");
                }
            }
        });
    }

}
