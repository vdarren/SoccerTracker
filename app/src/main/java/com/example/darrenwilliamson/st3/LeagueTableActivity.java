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

public class LeagueTableActivity extends AppCompatActivity {

    private String TAG = LeagueTableActivity.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);

        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new GetTable().execute();
    }



    private class GetTable extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(LeagueTableActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            MyHttpRequest sh = new MyHttpRequest();
            // Making a request to url and getting response
            String url = "http://api.football-data.org/v1/competitions/426/leagueTable";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray standing = jsonObj.getJSONArray("standing");

                    // looping through All Contacts
                    for (int i = 0; i < standing.length(); i++) {
                        JSONObject f = standing.getJSONObject(i);
                        String position = f.getString("position");
                        String teamName = f.getString("teamName");
                        String crestURI = f.getString("crestURI");
                        String playedGames = f.getString("playedGames");
                        String points = f.getString("points");
                        String goals = f.getString("goals");
                        String goalsAgainst = f.getString("goalsAgainst");
                        String goalDifference = f.getString("goalDifference");


                        // Phone node is JSON Object
                        //JSONObject result = f.getJSONObject("result");
                        //String goalsHomeTeam = result.getString("Home team goals");
                        //String goalsAwayTeam = result.getString("Away team goals");


                        // tmp hash map for single contact
                        HashMap<String, String> table = new HashMap<>();

                        // adding each child node to HashMap key => value
                        table.put("rank", position);
                        table.put("team", teamName);
                        table.put("points", points);
                        table.put("goals", goals);

                        // adding contact to contact list
                        contactList.add(table);
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

            ListAdapter adapter = new SimpleAdapter(LeagueTableActivity.this, contactList,
                    R.layout.activity_league_table, new String[]{ "rank","team","points"},
                    new int[]{R.id.pos, R.id.team, R.id.points});
            lv.setAdapter(adapter);
        }
    }
}