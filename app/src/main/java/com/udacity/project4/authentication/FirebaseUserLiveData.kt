package com.udacity.project4.authentication

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class FirebaseUserLiveData : LiveData<FirebaseUser?>() {


private fun getFirebaseAuthInstance() = FirebaseAuth.getInstance()

    private val firebaseAuth = getFirebaseAuthInstance()


    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        onAuthStateChanged(firebaseAuth.currentUser)
    }

    override fun onActive() {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onInactive() {
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

    private fun onAuthStateChanged(user: FirebaseUser?) {
        value = user
    }
}
