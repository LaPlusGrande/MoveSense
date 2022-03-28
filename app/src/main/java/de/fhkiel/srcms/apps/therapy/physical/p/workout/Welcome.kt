package de.fhkiel.srcms.apps.therapy.physical.p.workout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import de.fhkiel.srcms.apps.therapy.physical.R


class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val groupe_Btn = findViewById<Button>(R.id.idBtnGroupe)
//        val individual_Btn = findViewById<Button>(R.id.idBtnIndividual)

        groupe_Btn.setOnClickListener{
            Intent(this, GroupeEntry::class.java).also {
                startActivity(it)
            }
        }

    }

}