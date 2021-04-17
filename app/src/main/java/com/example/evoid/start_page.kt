package com.example.evoid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_start_page.*

class start_page : AppCompatActivity() {
    companion object{
        private const val RC_SIGN_IN = 120
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        var b = 0
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_start_page)
        cardView.setBackgroundResource(R.drawable.cardcorners)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                //Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            //Log.d(TAG, msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

        if (auth.currentUser != null)
        {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        showHideStart.setOnClickListener() {
            if (b == 0)
            {
                passwordStart.transformationMethod = HideReturnsTransformationMethod.getInstance();
                b = 1;
                showHideStart.visibility = View.INVISIBLE
                showHide2Start.visibility = View.VISIBLE

            }

        }

        showHide2Start.setOnClickListener(){
            if (b==1)
            {
                passwordStart.transformationMethod = PasswordTransformationMethod.getInstance();
                b=0
                showHide2Start.visibility = View.INVISIBLE
                showHideStart.visibility = View.VISIBLE
            }
        }


        googleSignInStart.setOnClickListener {
            signIn()
        }



        loginStart.setOnClickListener {
            val emailId = emailIdStart.text.toString()
            val password = passwordStart.text.toString()

            if (validateForm(emailId, password)) //check if all the details are entered
            {
                signUser(emailId, password, auth)

            }


        }

        registerStart.setOnClickListener {
            val intent = Intent(this, RegisterUser::class.java)
            startActivity(intent)

        }

        forgotPasswordStart.setOnClickListener {
            val intent = Intent(this, forgotPassword::class.java)
            startActivity(intent)


        }


    }

    private fun validateForm(emailid: String, password: String): Boolean
    {
        if (emailid.isEmpty())
        {
            emailIdStart.setError("Email should not be empty")
            return false
        }
        if (password.isEmpty())
        {
            passwordStart.setError("Password should not be empty")
            return false
        }
        else
            return true

    }

    private fun signUser(emailid: String, password: String, auth: FirebaseAuth) {
        auth.signInWithEmailAndPassword(emailid, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Signed In Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)

                } else {
                    Log.w("failure", "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Incorrect Email/Password", Toast.LENGTH_SHORT).show()
                    //Toast.makeText(this, "E-mail Id/Password Incorrect",Toast.LENGTH_SHORT).show()

                }

            }



    }

    private fun signIn() {
        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                //Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                //Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithCredential:success")
                    val currentUser = auth.currentUser
                    val name = currentUser!!.displayName!!.toString()
                    val arr: List<String> = name.split(" ")
                    val fname = arr[0]
                    val lname = arr[1]
                    val user = com.example.evoid.User(currentUser.uid,
                        fname,
                        lname,
                        91,
                        currentUser.email!!,
                        "",
                        currentUser.photoUrl!!.toString()
                    )
                    firestoreClass().registerUserViaGoogle(this, user)
                    Toast.makeText(this, "Signed In Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Log.w("failure", "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Incorrect Email/Password", Toast.LENGTH_SHORT).show()

                }
            }
    }






}