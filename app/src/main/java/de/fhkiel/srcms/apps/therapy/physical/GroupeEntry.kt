package de.fhkiel.srcms.apps.therapy.physical

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.design.activity.RobotActivity

class GroupeEntry : RobotActivity(), RobotLifecycleCallbacks {

    private lateinit var hard_btn : Button
    private lateinit var easy_btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QiSDK.register(this, this)

        setContentView(R.layout.activity_groupe_entry)

        val back_btn = findViewById<Button>(R.id.idBtnBack0)
        hard_btn = findViewById<Button>(R.id.idBtnHardAnimation)
        easy_btn = findViewById<Button>(R.id.idBtnEasyAnimation)

        back_btn.setOnClickListener{
            Intent(this, Welcome:: class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onRobotFocusGained(qiContext: QiContext?) {

        hard_btn.setOnClickListener {
            Intent (this, Perform:: class.java).also{
            it.putExtra("keyPerform","valueHardPerform")
            startActivity(it)
            }
        }
        easy_btn.setOnClickListener{

            Intent (this, Perform:: class.java).also {
                it.putExtra("keyPerform", "valueEasyPerform")
                startActivity(it)
            }
        }
    }

    override fun onRobotFocusLost() {
        TODO("Not yet implemented")
    }

    override fun onRobotFocusRefused(reason: String?) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this)
        super.onDestroy()
    }
}