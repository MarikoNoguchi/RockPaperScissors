package edu.mnstate.cw3967me.rockpaperscissors;

/*
 show a dialog with a picture of myself
 Mariko Noguchi
 11/28/2016
 */

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AboutMyselfDialogFragment extends DialogFragment {
    Button close;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_about_myself, container, false);
        close = (Button) rootView.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return rootView;
    }


}
