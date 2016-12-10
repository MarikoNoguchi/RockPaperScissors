package edu.mnstate.cw3967me.rockpaperscissors;

/*
 set an Array of Rankings objects into a ListView
 Mariko Noguchi
 11/28/2016
 */

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RankingCustomAdapter extends ArrayAdapter<Ranking> {
    PackageManager packageManager;
    LayoutInflater inflater;
    List<Ranking> ranking; //array of Ranking objects

    public RankingCustomAdapter(Context context, int textViewResourceId, List<Ranking> ranking) {
        super(context, textViewResourceId, ranking);
        this.ranking = ranking;
        inflater = LayoutInflater.from(context);
        packageManager = context.getPackageManager();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.ranking_item, null);
        TextView rank = (TextView) view.findViewById(R.id.rank);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView score = (TextView) view.findViewById(R.id.score);

        rank.setText(Integer.toString(i+1));
        name.setText(ranking.get(i).getName());
        score.setText(ranking.get(i).getScore()+" points");

        return view;
    }
}