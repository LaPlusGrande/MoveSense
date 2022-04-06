package de.fhkiel.srcms.apps.therapy.physical.p.workout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Welcome : AppCompatActivity() {

    private var TAG = Welcome::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val groupe_Btn = findViewById<Button>(R.id.idBtnGroupe)
//        val individual_Btn = findViewById<Button>(R.id.idBtnIndividual)

        groupe_Btn.setOnClickListener{
            Intent(this, GroupEntry::class.java).also {
                startActivity(it)
            }
        }

//        // get battery status
//        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
//            this.registerReceiver(null, ifilter)
//        }
//
//        val batteryPct: Float? = batteryStatus?.let { intent ->
//            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
//            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
//            level * 100 / scale.toFloat()
//        }
//        Log.i(TAG.toString(), batteryPct.toString() + "%")
    }



}