package com.example.darrenwilliamson.st3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by DarrenWilliamson on 10/04/2017.
 */

public class MainHubActivity extends AppCompatActivity {

    Button _btnLeagueTable;
    Button _btnFixtures;
    Button _btnCurrentTeam;
    Button _btnNextGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);

        //Referencing button
        final Button _btnLeagueTable = (Button) findViewById(R.id.btn_league_table);
        final Button _btnFixtures = (Button) findViewById(R.id.btn_fixtures);
        final Button _btnCurrentTeam = (Button) findViewById(R.id.btn_current_team);
        final Button _btnNextGame = (Button) findViewById(R.id.btn_next_game);


        // Intent for going to next page
        _btnLeagueTable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainHubActivity.this, LeagueTableActivity.class);
                startActivity(intent);
            }
        });

        // Intent for going to next page
        _btnFixtures.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainHubActivity.this, ActivityFixtures.class);
                startActivity(intent);
            }
        });


    }
}
