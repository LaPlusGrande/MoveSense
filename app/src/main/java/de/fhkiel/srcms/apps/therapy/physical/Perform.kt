package de.fhkiel.srcms.apps.therapy.physical

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.bumptech.glide.Glide

class Perform : RobotActivity(), RobotLifecycleCallbacks {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QiSDK.register(this, this)
        setContentView(R.layout.activity_perform)

        val images = resources.obtainTypedArray(R.array.gif_array)
        val choice : Int = (Math.random()*images.length()).toInt()
        val back_btn = findViewById<Button>(R.id.idBtnBack1)
        val imageView = findViewById<ImageView>(R.id.myGifIV)

        Glide.with(this@Perform).load(images.getResourceId(choice, R.drawable.banana))
            .into(imageView)
        images.recycle()

        back_btn.setOnClickListener{
            Intent(this, Welcome:: class.java).also {
                startActivity(it)
            }
        }

    }

    @Override
    override fun onRobotFocusGained(qiContext: QiContext?) {

        val bundle : Bundle? =intent.extras
        if(bundle == null) {
            Log.d("TAG","an error occured")
        }else{
            var extra = intent.getStringExtra("keyPerform")

            if(extra.equals("valueHardPerform") ) {

                val iter : Int = 2
                val arr : IntArray = intArrayOf(10,7,6,1,4,9,8,2,5,12,0)
                val myExercise = Exercise()
                if (qiContext != null) {
                    myExercise.doExercise(qiContext, arr, iter)
                }
            }
            if(extra.equals("valueEasyPerform")) {
                val iter : Int = 2
                val arr : IntArray = intArrayOf(10,7,6,5,11,3,12,0)
                val myExercise = Exercise()
                if (qiContext != null) {
                    myExercise.doExercise(qiContext, arr, iter)
                }
            }
        }
    }

    @Override
    override fun onRobotFocusLost() {

    }

    @Override
    override fun onRobotFocusRefused(reason: String?) {

    }

    @Override
    override fun onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this)
        super.onDestroy()
    }
}

