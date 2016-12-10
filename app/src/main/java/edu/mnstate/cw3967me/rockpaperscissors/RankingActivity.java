package edu.mnstate.cw3967me.rockpaperscissors;

/*
show a ranking
 Mariko Noguchi
 11/28/2016
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class RankingActivity extends AppCompatActivity {
    private RankingDataSource dataSource;
    Globals globals;
    ListView ranking_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globals = (Globals) this.getApplication();

        dataSource = new RankingDataSource(this);
        dataSource.open();

        //get intent
        Intent intent = getIntent();
        String event = intent.getStringExtra("event");
        System.out.println(event);
        if (event.equals("exit")){
            add();
        }
        else if (event.equals("show")) {
            setContentView(R.layout.activity_ranking);


            List<Ranking> values = dataSource.getAllRankings();

            ranking_list = (ListView) findViewById(R.id.ranking_list);
            RankingCustomAdapter rankingcustomAdapter = new RankingCustomAdapter(getApplication(), 0, values);
            ranking_list.setAdapter(rankingcustomAdapter);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ranking_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        List<Ranking> values = dataSource.getAllRankings();
        switch (item.getItemId()) {
            case R.id.all_ranking:
                values = dataSource.getAllRankings();
                String msg = "";
                for(int i=0; i<values.size(); i++){
                    msg += values.get(i).getId()+", "+values.get(i).getName()+","+values.get(i).getScore()+"\n";
                }
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

                ranking_list = (ListView) findViewById(R.id.ranking_list);
                RankingCustomAdapter rankingcustomAdapter = new RankingCustomAdapter(getApplication(), 0, values);
                ranking_list.setAdapter(rankingcustomAdapter);
                return true;
            case R.id.top_three:
                values = dataSource.getTopThreeRankings();
                ranking_list = (ListView) findViewById(R.id.ranking_list);
                rankingcustomAdapter = new RankingCustomAdapter(getApplication(), 0, values);
                ranking_list.setAdapter(rankingcustomAdapter);
                return true;
            case R.id.your_score:
                values = dataSource.getYourRankings(globals.name);
                ranking_list = (ListView) findViewById(R.id.ranking_list);
                rankingcustomAdapter = new RankingCustomAdapter(getApplication(), 0, values);
                ranking_list.setAdapter(rankingcustomAdapter);
                return true;
            case R.id.delete:
                dataSource.deleteFirstRanking();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void add() {
        dataSource.createRanking(globals.name, Integer.toString(globals.score));

        //exit the application
        this.moveTaskToBack(true);
    }


   /* public void delete() {
       /* if (getListAdapter().getCount() > 0) {
            ranking = (Ranking) getListAdapter().getItem(0);
            dataSource.deleteRanking(ranking);
            adapter.remove(ranking);
        }
        adapter.notifyDataSetChanged();*/
        /*List<Ranking> ranking = dataSource.getAllRankings();
        for(int i=0; i<ranking.size(); i++) {
            dataSource.deleteRanking(ranking.get(i));
        }
    }*/

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}
