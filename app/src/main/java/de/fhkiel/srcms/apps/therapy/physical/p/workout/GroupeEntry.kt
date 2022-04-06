package de.fhkiel.srcms.apps.therapy.physical.p.workout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.aldebaran.qi.Future
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.actuation.Actuation
import com.aldebaran.qi.sdk.`object`.actuation.Frame
import com.aldebaran.qi.sdk.`object`.geometry.Transform
import com.aldebaran.qi.sdk.`object`.geometry.TransformTime
import com.aldebaran.qi.sdk.`object`.geometry.Vector3
import com.aldebaran.qi.sdk.`object`.human.*
import com.aldebaran.qi.sdk.`object`.humanawareness.HumanAwareness
import com.aldebaran.qi.sdk.design.activity.RobotActivity

class GroupeEntry : RobotActivity(), RobotLifecycleCallbacks {

    private lateinit var hard_btn : Button
    private lateinit var easy_btn : Button

    private var TAG = GroupeEntry::class.java

    private var humanAwareness: HumanAwareness? = null
    private var qiContext: QiContext? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QiSDK.register(this, this)

        setContentView(R.layout.activity_groupe_entry)

        val back_btn = findViewById<Button>(R.id.back_button)
        hard_btn = findViewById<Button>(R.id.button_hard_animation)
        easy_btn = findViewById<Button>(R.id.button_easy_animation)

        back_btn.setOnClickListener{
            Intent(this, Welcome:: class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onRobotFocusGained(qiContext: QiContext?) {

        this.qiContext = qiContext
        humanAwareness = qiContext!!.humanAwareness
        findHumansAround()

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

    }

    override fun onRobotFocusRefused(reason: String?) {

    }

    override fun onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this)
        super.onDestroy()
    }

    fun findHumansAround() {
        // Get the humans around the robot.
        val humansAroundFuture: Future<List<Human>>? = humanAwareness?.async()?.humansAround
        humansAroundFuture?.andThenConsume { humansAround: List<Human> -> Log.i(TAG.toString(), "${humansAround.size} human(s) around.")}

        // Retrieving characteristics
        humansAroundFuture?.andThenConsume { humansAround ->
            Log.i(TAG.toString(), "${humansAround.size} human(s) around.")
            retrieveCharacteristics(humansAround)
        }
    }

    fun retrieveCharacteristics(humans: List<Human>) {
        val actuation: Actuation = qiContext!!.actuation
        val robotFrame: Frame = actuation.robotFrame()
        humans.forEachIndexed { index, human ->
            // Get the human.
            val humanFrame: Frame = human.getHeadFrame()
            // Compute the distance.
            val distance = computeDistance(humanFrame, robotFrame)
            // Get the characteristics.
            val age: Int = human.getEstimatedAge().getYears()
            val gender: Gender = human.getEstimatedGender()
            val pleasureState: PleasureState = human.getEmotion().getPleasure()
            val excitementState: ExcitementState = human.getEmotion().getExcitement()
            val engagementIntentionState: EngagementIntentionState = human.getEngagementIntention()
            val smileState: SmileState = human.getFacialExpressions().getSmile()
            val attentionState: AttentionState = human.getAttention()
            Log.i(TAG.toString(), "----- Human $index -----")
            Log.i(TAG.toString(), "Age: $age year(s)")
            Log.i(TAG.toString(), "Gender: $gender")
            Log.i(TAG.toString(), "Pleasure state: $pleasureState")
            Log.i(TAG.toString(), "Excitement state: $excitementState")
            Log.i(TAG.toString(), "Engagement state: $engagementIntentionState")
            Log.i(TAG.toString(), "Smile state: $smileState")
            Log.i(TAG.toString(), "Attention state: $attentionState")
            // Display the distance between the human and the robot.
            Log.i(TAG.toString(), "Distance: $distance meter(s).")
        }
    }

    fun computeDistance(humanFrame: Frame, robotFrame: Frame?): Double {
        // Here we will compute the distance between the human and the robot.
        // Get the TransformTime between the human frame and the robot frame.
        val transformTime: TransformTime = humanFrame.computeTransform(robotFrame)
        // Get the transform.
        val transform: Transform = transformTime.getTransform()
        // Get the translation.
        val translation: Vector3 = transform.translation
        // Get the x and y components of the translation.
        val x: Double = translation.getX()
        val y: Double = translation.getY()
        val z: Double = translation.getZ()

        // Compute the distance and return it.
        return Math.sqrt(x * x + y * y)
    }
}