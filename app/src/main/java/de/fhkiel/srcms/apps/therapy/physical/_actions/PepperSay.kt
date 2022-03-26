package de.fhkiel.srcms.apps.therapy.physical._actions

import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.`object`.conversation.Say
import com.aldebaran.qi.sdk.builder.SayBuilder

class PepperSay : PepperAction() {

    fun doSay (qiContext: QiContext, string: String){
        val say: Say = SayBuilder.with(qiContext)
            .withText(string)
            .build()
        sayFuture(say.async().run())
    }
}