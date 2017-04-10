package com.example.darrenwilliamson.st3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by DarrenWilliamson on 06/04/2017.
 */
public class StartupActivity extends AppCompatActivity {

    Button _btnStartPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        //Referencing button
        final Button _btnStartPage = (Button) findViewById(R.id.btn_startPage);


        // Intent for going to next page
        _btnStartPage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StartupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

