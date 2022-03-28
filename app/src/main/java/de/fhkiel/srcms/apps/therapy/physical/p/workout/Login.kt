package de.fhkiel.srcms.apps.therapy.physical.p.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import de.fhkiel.srcms.apps.therapy.physical.R

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val passwordEdt = findViewById<EditText>(R.id.idEdtPassword)
        val loginNameEdt = findViewById<EditText>(R.id.idEdtLoginName)
        val loginBtn = findViewById<Button>(R.id.idBtnLogin)

        loginBtn.setOnClickListener {

            val loginName : String = loginNameEdt.text.toString()
            val password : String = passwordEdt.text.toString()

            if(loginName.isEmpty()&& password.isEmpty()){
                var toast = Toast.makeText(this,"Bitte alle Felder ausfüllen",Toast.LENGTH_SHORT)
            }else if (loginName == "name" && password == "0000"){
                Intent(this, GroupeEntry:: class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}