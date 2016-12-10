package edu.mnstate.cw3967me.rockpaperscissors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 set an Array of Result objects into a ListView
 Mariko Noguchi
 11/28/2016
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private List<Result> resultList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user, computer, round;

        public MyViewHolder(View view) {
            super(view);
            user = (TextView) view.findViewById(R.id.user);
            computer = (TextView) view.findViewById(R.id.computer);
            round = (TextView) view.findViewById(R.id.round);
        }
    }

    public CustomAdapter(List<Result> studentList) {
        this.resultList = studentList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.results_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.user.setText(result.getUser());
        holder.computer.setText(result.getComputer());
        holder.round.setText(result.getRound());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
