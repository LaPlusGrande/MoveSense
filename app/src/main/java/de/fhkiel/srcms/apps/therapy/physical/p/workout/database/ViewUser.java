package de.fhkiel.srcms.apps.therapy.physical.p.workout.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import de.fhkiel.srcms.apps.therapy.physical.p.workout.R;

import java.util.ArrayList;

public class ViewUser extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<UserModal> userModalArrayList;
    private DBHandler dbHandler;
    private UserRVAdapter userRVAdapter;
    private RecyclerView userRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        // initializing our all variables.
        userModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewUser.this);

        // getting array list from db handler class.
        userModalArrayList = dbHandler.readUser();

        // on below line passing our array lost to our adapter class.
        userRVAdapter = new UserRVAdapter(userModalArrayList, ViewUser.this);
        userRV = findViewById(R.id.idRVUser);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewUser.this, RecyclerView.VERTICAL, false);
        userRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        userRV.setAdapter(userRVAdapter);
    }
}