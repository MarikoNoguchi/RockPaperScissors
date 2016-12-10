package edu.mnstate.cw3967me.rockpaperscissors;
/**
 * Show rock-paper-scissors game fragment
 Mariko Noguchi
 11/28/2016
 */

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GameFragment extends Fragment implements View.OnClickListener {
    Globals globals;
    TextView lblName;
    ImageView imgPaper;
    ImageView imgRock;
    ImageView imgScissors;

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        globals = (Globals) getActivity().getApplication();
        //inflate the layout for this fragment
        View view = inf.inflate(R.layout.game_fragment, container, false);

        //get references for each resource on the interface
        lblName = (TextView) view.findViewById(R.id.lblName);
        imgPaper = (ImageView) view.findViewById(R.id.imgPaper);
        imgRock = (ImageView) view.findViewById(R.id.imgRock);
        imgScissors = (ImageView) view.findViewById(R.id.imgScissors);

        if (!globals.name.equals("YOU")) lblName.setText(globals.name);

        // set the listeners
        imgPaper.setOnClickListener(this);
        imgRock.setOnClickListener(this);
        imgScissors.setOnClickListener(this);

        //set menu
        setHasOptionsMenu(true);

        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgRock:
                Intent intentG = new Intent(getActivity(), resultActivity.class);
                intentG.putExtra("userHand", 0);
                startActivity(intentG);
                break;
            case R.id.imgScissors:
                Intent intentC = new Intent(getActivity(), resultActivity.class);
                intentC.putExtra("userHand", 1);
                startActivity(intentC);
                break;
            case R.id.imgPaper:
                Intent intentP = new Intent(getActivity(), resultActivity.class);
                intentP.putExtra("userHand", 2);
                startActivity(intentP);
                break;
        }
    }


    //show option menu
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        ResultsFragment settingsFragment = (ResultsFragment) getFragmentManager()
                .findFragmentById(R.id.results_fragment);
        // display the appropriate menu
        if (settingsFragment == null) {
            // SettingsFragment is not in the layout
            inflater.inflate(R.menu.my_options_menu, menu);
        } else {
            // SettingsFragment is in the layout
            inflater.inflate(R.menu.my_options_twopane, menu);
        }
    }

    //if the user chose menu a item
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.results:
                startActivity(new Intent(getActivity(), resultsActivity.class));
                return true;
            case R.id.ranking:
                Intent intent = new Intent(getActivity(), RankingActivity.class);
                intent.putExtra("event","show");
                startActivity(intent);
                return true;
            case R.id.about_myself:
                AboutMyselfDialogFragment aboutMyself = new AboutMyselfDialogFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                aboutMyself.show(ft,"dialog");
                return true;
            case R.id.preferences:
                showPromptDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //show a dialog
    private void showPromptDialog() {
        Context context = getActivity();
        LayoutInflater li = LayoutInflater.from(context);

        View getInfoView = li.inflate(R.layout.set_user_name, null);
        AlertDialog.Builder alertDlgBld = new AlertDialog.Builder(context);
        alertDlgBld.setView(getInfoView);

        final EditText txtName = (EditText) getInfoView.findViewById(R.id.txtName);

        //set dialog message
        alertDlgBld.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        globals.name = txtName.getText().toString();
                        lblName.setText(globals.name);

                        /*
                        Fragment frg = getFragmentManager().findFragmentById(R.id.results_fragment);
                        FragmentTransaction tr = getFragmentManager().beginTransaction();
                        tr.replace(R.id.results_fragment, frg);
                        tr.commit();
                        */
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDlgBld.create();
        alertDialog.show();
    }
}
