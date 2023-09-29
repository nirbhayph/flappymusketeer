package com.nirbhay.flappymusketeer.common

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

/**
 * Created by Nirbhay Pherwani on 9/29/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */

object Analytics {
    var firebaseAnalytics: FirebaseAnalytics? = Firebase.analytics

    fun logEvent(event: Events, params: Bundle? = null) {
        firebaseAnalytics?.logEvent(event.name, params)
    }
}