package de.fhkiel.srcms.apps.therapy.physical._database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.fhkiel.srcms.apps.therapy.physical.Individual;
import de.fhkiel.srcms.apps.therapy.physical.PerformActivity;

import de.fhkiel.srcms.apps.therapy.physical.R;

public class UpdateUser extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText userNameEdt, iterationEdt, animationOneEdt, animationTwoEdt;
    private Button updateUserBtn, deleteUserBtn, playBtn;
    private DBHandler dbHandler;
    static public String userName;
    String iteration, animationOne, animationTwo;

    static public int iter;
    static public int[] array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        // initializing all our variables.
        userNameEdt = findViewById(R.id.idEdtUserName);
        iterationEdt = findViewById(R.id.idEdtIteration);
        animationOneEdt = findViewById(R.id.idEdtAnimationOne);
        animationTwoEdt = findViewById(R.id.idEdtAnimationTwo);
        updateUserBtn = findViewById(R.id.idBtnUpdateUser);
        deleteUserBtn = findViewById(R.id.idBtnDelete);
        playBtn = findViewById(R.id.idBtnPalybutton);

        // on below line we are initialing our dbhandler class.
        dbHandler = new DBHandler(UpdateUser.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        userName = getIntent().getStringExtra("name");
        iteration = getIntent().getStringExtra("iteration");
        animationOne = getIntent().getStringExtra("animation one");
        animationTwo = getIntent().getStringExtra("animation two");

        // setting data to edit text
        // of our update activity.
        userNameEdt.setText(userName);
        iterationEdt.setText(iteration);
        animationOneEdt.setText(animationOne);
        animationTwoEdt.setText(animationTwo);

        iterationEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateUser.this, "Bitte eine Zahl eingeben", Toast.LENGTH_LONG).show();
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
                Toast.makeText(UpdateUser.this, msg, Toast.LENGTH_LONG).show();
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
                Toast.makeText(UpdateUser.this, msg, Toast.LENGTH_LONG).show();
            }
        });

        // adding on click listener to our update course button.
        updateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHandler.updateUser(userName, userNameEdt.getText().toString(), animationTwoEdt.getText().toString(), iterationEdt.getText().toString(), animationOneEdt.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateUser.this, "Benutzer wurde angepasst", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateUser.this, Individual.class);
                startActivity(i);
            }
        });

        // adding on click listener for delete button to delete our course.
        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                dbHandler.deleteUser(userName);
                Toast.makeText(UpdateUser.this, "Benutzer gelöscht", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateUser.this, Individual.class);
                startActivity(i);
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                array = new int [3];

                iter = Integer.parseInt(iteration);
                array[0] = Integer.parseInt(animationOne);
                array[1] = Integer.parseInt(animationTwo);
                array[2] = 0;

                Intent indiIntent = new Intent(UpdateUser.this, PerformActivity.class);
                indiIntent.putExtra("keyPerform", "valueIndividual");
                startActivity(indiIntent);
            }
        });
    }
}