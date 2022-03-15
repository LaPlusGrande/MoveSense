package de.fhkiel.srcms.apps.therapy.physical._database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.fhkiel.srcms.apps.therapy.physical.Demo;
import de.fhkiel.srcms.apps.therapy.physical.Individual;

import de.fhkiel.srcms.apps.therapy.physical.R;

public class AddUser extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText userNameEdt, iterationEdt, animationOneEdt, animationTwoEdt;
    private Button addUserBtn, backBtn, demoBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        // initializing all our variables.
        userNameEdt = findViewById(R.id.idEdtUserName);
        iterationEdt = findViewById(R.id.idEdtIteration);
        animationOneEdt = findViewById(R.id.idEdtAnimationOne);
        animationTwoEdt = findViewById(R.id.idEdtAnimationTwo);
        addUserBtn = findViewById(R.id.idBtnAddUser);
        backBtn = findViewById(R.id.idBtnBack);
        demoBtn = findViewById(R.id.idBtnDemo);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(AddUser.this);

        iterationEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddUser.this, "Bitte eine Zahl eingeben", Toast.LENGTH_LONG).show();
            }
        });

        animationOneEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "0 - Keine Übung\n" +
                        "1 - Äpfelpflücken\n" +
                        "2 - Arme Rotieren\n" +
                        "3 - Arme seitlich heben\n" +
                        "4 - Boxen\n" +
                        "5 - Klatschen\n" +
                        "6 - Hände öffnen und Ellenbogen strecken\n" +
                        "7 - Ellenbogen strecken\n" +
                        "8 - Oberkörper vorbeugen und Arme verfolgen\n" +
                        "9 - Oberkörper vorbeugen\n" +
                        "10 - Hände öffnen und schließen\n" +
                        "11 - Oberkörper seitlich beugen";
                Toast.makeText(AddUser.this, msg, Toast.LENGTH_LONG).show();
            }
        });

        animationTwoEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "0 - Keine Übung\n" +
                        "1 - Äpfelpflücken\n" +
                        "2 - Arme Rotieren\n" +
                        "3 - Arme seitlich heben\n" +
                        "4 - Boxen\n" +
                        "5 - Klatschen\n" +
                        "6 - Hände öffnen und Ellenbogen strecken\n" +
                        "7 - Ellenbogen strecken\n" +
                        "8 - Oberkörper vorbeugen und Arme verfolgen\n" +
                        "9 - Oberkörper vorbeugen\n" +
                        "10 - Hände öffnen und schließen\n" +
                        "11 - Oberkörper seitlich beugen";
                Toast.makeText(AddUser.this, msg, Toast.LENGTH_LONG).show();
            }
        });

        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // below line is to get data from all edit text fields.
                String userName = userNameEdt.getText().toString();
                String iteration = iterationEdt.getText().toString();
                String animationOne = animationOneEdt.getText().toString();
                String animationTwo = animationTwoEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (userName.isEmpty() && iteration.isEmpty() && animationOne.isEmpty() && animationTwo.isEmpty()) {
                    Toast.makeText(AddUser.this, "Bitte alle Felder ausfüllen. Wiederholungen und Übungen werden in Zahlen angeben", Toast.LENGTH_SHORT).show();
                    return;
                }
                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewUser(userName, animationOne, animationTwo, iteration);

                // after adding the data we are displaying a toast message.
                Toast.makeText(AddUser.this, "Benutzer wurde hinzugefügt.", Toast.LENGTH_SHORT).show();
                userNameEdt.setText("");
                animationOneEdt.setText("");
                iterationEdt.setText("");
                animationTwoEdt.setText("");
            }
        });

        demoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddUser.this, Demo.class);
                startActivity(i);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddUser.this, Individual.class);
                startActivity(i);
            }
        });
    }
}