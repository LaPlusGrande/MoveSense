package de.fhkiel.srcms.apps.therapy.physical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.fhkiel.srcms.apps.therapy.physical.R;

import de.fhkiel.srcms.apps.therapy.physical._database.AddUser;
import de.fhkiel.srcms.apps.therapy.physical._database.ViewUser;

public class Individual extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private Button backBtn, readUserBtn, addBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);

        readUserBtn = findViewById(R.id.idBtnReadUser);
        addBtn = findViewById(R.id.idBtnAddUser);
        backBtn = findViewById(R.id.idBtnBack);

        readUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Individual.this, ViewUser.class);
                startActivity(i);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Individual.this, AddUser.class);
                startActivity(i);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Individual.this, Welcome.class);
                startActivity(i);
            }
        });

    }
}