package com.example.darrenwilliamson.st3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DarrenWilliamson on 10/04/2017.
 */

public class ActivityFixtures extends AppCompatActivity {


    private String TAG = ActivityFixtures.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> fixtureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);

        fixtureList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new GetTable().execute();
    }


    private class GetTable extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(ActivityFixtures.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            MyHttpRequest sh = new MyHttpRequest();
            // Making a request to url and getting response
            String url = "http://api.football-data.org/v1/competitions/426/fixtures?matchday=33";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray fixtures = jsonObj.getJSONArray("fixtures");

                    // looping through All Contacts
                    for (int i = 0; i < fixtures.length(); i++) {
                        JSONObject f = fixtures.getJSONObject(i);

                        String date = f.getString("date");
                        String status = f.getString("status");
                        String matchday = f.getString("matchday");
                        String homeTeamName = f.getString("homeTeamName");
                        String awayTeamName = f.getString("awayTeamName");
                        String result = f.getString("result");

                        // Phone node is JSON Object
                        JSONObject g = f.getJSONObject("result");
                        String goalsHomeTeam = g.getString("goalsHomeTeam");
                        String goalsAwayTeam = g.getString("goalsAwayTeam");


                        // tmp hash map for single contact
                        HashMap<String, String> table = new HashMap<>();

                        // adding each child node to HashMap key => value
                        table.put("home", homeTeamName);
                        table.put("away", awayTeamName);
                        table.put("day", date);
                        table.put("status_", status);

                        // adding contact to contact list
                        fixtureList.add(table);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            ListAdapter adapter = new SimpleAdapter(ActivityFixtures.this, fixtureList,
                    R.layout.activity_fixtures, new String[]{"home", "away", "day"},
                    new int[]{R.id.email, R.id.mobile, R.id.status});
            lv.setAdapter(adapter);
        }

    }
}


