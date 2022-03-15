package de.fhkiel.srcms.apps.therapy.physical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText passwordEdt,loginnameEdt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwordEdt = findViewById(R.id.idEdtPassword);
        loginnameEdt = findViewById(R.id.idEdtLoginName);
        loginBtn = findViewById(R.id.idBtnLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String loginname= loginnameEdt.getText().toString();
                String password= passwordEdt.getText().toString();

                if (loginname.isEmpty() && password.isEmpty()){
                    Toast.makeText(Login.this,"Bitte alle Felder ausfüllen", Toast.LENGTH_LONG).show();
                    return;
                }else if (loginname.equals("name") && password.equals("0000"))
                {
                    // TODO: user name access
                    Intent loginintent = new Intent(Login.this,Welcome.class);
                    startActivity(loginintent);
                    // TODO: show Toast if user is not available

                }

            }
        });


    }
}