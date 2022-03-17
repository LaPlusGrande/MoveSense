package de.fhkiel.srcms.apps.therapy.physical.p.workout.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.fhkiel.srcms.apps.therapy.physical.R;

import java.util.ArrayList;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.ViewHolder>{

    // variable for our array list and context
    private ArrayList<UserModal> userModalArrayList;
    private Context context;

    // constructor
    public UserRVAdapter(ArrayList<UserModal> userModalArrayList, Context context) {
        this.userModalArrayList = userModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        UserModal modal = userModalArrayList.get(position);
        holder.userNameTV.setText("Name:"+modal.getUserName());
        holder.iterationTV.setText("Wiederholung:"+modal.getIteration());
        holder.animationOneTV.setText("Erste Übung:"+modal.getAnimationOne());
        holder.animationTwoTV.setText("Zweite Übung:"+modal.getAnimationTwo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateUser.class);

                // below we are passing all our values.
                i.putExtra("name", modal.getUserName());
                i.putExtra("iteration", modal.getAnimationTwo());
                i.putExtra("animation one", modal.getIteration());
                i.putExtra("animation two", modal.getAnimationOne());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list44
        return userModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView userNameTV, iterationTV, animationOneTV, animationTwoTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            userNameTV = itemView.findViewById(R.id.idTVUserName);
            iterationTV = itemView.findViewById(R.id.idTVIteration);
            animationOneTV = itemView.findViewById(R.id.idTVAnimationOne);
            animationTwoTV = itemView.findViewById(R.id.idTVAnimationTwo);
        }
    }
}
