package com.udacity.project4.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.udacity.project4.databinding.ActivityAuthenticationBinding
import com.udacity.project4.locationreminders.RemindersActivity


class AuthenticationActivity : AppCompatActivity() {


private val binding by lazy {
    ActivityAuthenticationBinding.inflate(layoutInflater)
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signInButton.setOnClickListener {
            launchSignInFlow()
        }
    }

    private fun launchSignInFlow() {
        val providers = listOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        startActivityForResult(signInIntent, SIGN_IN_RESULT_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != SIGN_IN_RESULT_CODE) {
            return
        }

        val response = IdpResponse.fromResultIntent(data)
        if (resultCode == Activity.RESULT_OK) {
            onSignInSuccess()
        } else {
            onSignInFailure(response?.error?.errorCode)
        }
    }

    private fun onSignInSuccess() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        Log.i(TAG, "Successfully signed in user ${currentUser?.displayName}!")
        Toast.makeText(this, "Successfully signed in", Toast.LENGTH_SHORT).show()
        startReminderActivity()
    }

    private fun onSignInFailure(errorCode: Int?) {
        Log.i(TAG, "Sign in unsuccessful $errorCode")
        Toast.makeText(this, "Sign in unsuccessful", Toast.LENGTH_SHORT).show()
    }


    private fun startReminderActivity() {
        startActivity<RemindersActivity>()
        finish()
    }

    inline fun <reified T : Activity> Activity.startActivity() {
        startActivity(Intent(this, T::class.java))
    }

    companion object {
        const val TAG = "AuthenticationActivity"
        const val SIGN_IN_RESULT_CODE = 1001
    }

}
