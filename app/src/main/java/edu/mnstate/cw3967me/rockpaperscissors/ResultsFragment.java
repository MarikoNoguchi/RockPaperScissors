package edu.mnstate.cw3967me.rockpaperscissors;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
show a list of results
 Mariko Noguchi
 11/28/2016
 */
public class ResultsFragment extends Fragment {

    Globals globals;
    TextView name;

    private List<Result> resultList;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState) {
        globals = (Globals) getActivity().getApplication();
        resultList = globals.results;

        //inflate the layout for this fragment
        View view = inf.inflate(R.layout.results_fragment, container, false);

        //get and set the user's name
        name = (TextView) view.findViewById(R.id.name);
        name.setText(globals.name);



        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        customAdapter = new CustomAdapter(resultList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(customAdapter);

        customAdapter.notifyDataSetChanged();

        return view;
    }
}

