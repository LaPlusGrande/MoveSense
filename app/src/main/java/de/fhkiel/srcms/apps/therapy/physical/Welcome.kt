package de.fhkiel.srcms.apps.therapy.physical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.actuation.Animate
import com.aldebaran.qi.sdk.`object`.actuation.Animation
import com.aldebaran.qi.sdk.`object`.conversation.Say
import com.aldebaran.qi.sdk.builder.AnimateBuilder
import com.aldebaran.qi.sdk.builder.AnimationBuilder
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.aldebaran.qi.sdk.design.activity.RobotActivity


class Welcome : RobotActivity(), RobotLifecycleCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QiSDK.register(this, this)
        setContentView(R.layout.activity_welcome)

        val groupe_Btn = findViewById<Button>(R.id.idBtnGroupe)
//        val individual_Btn = findViewById<Button>(R.id.idBtnIndividual)

        groupe_Btn.setOnClickListener{
            Intent(this, GroupeEntry::class.java).also {
                startActivity(it)
            }
        }

    }

    override fun onRobotFocusGained(qiContext: QiContext?) {

//        val say: Say = SayBuilder.with(qiContext) // Create the builder with the context.
//            .withText("Hello human!") // Set the text to say.
//            .build() // Build the say
//            say.run()
//
//        val myAirboxing: Animation = AnimationBuilder.with(qiContext)
//            .withResources(R.raw.airboxing)
//            .build()
//        Log.d("TAG","build raw")
//
//        val animate: Animate = AnimateBuilder.with(qiContext)
//            .withAnimation(myAirboxing)
//            .build()
//        Log.d("TAG","build animate")
//
//        animate.async().run()
//        Log.d("TAG","run animate")
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