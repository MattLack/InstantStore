package ufrpe.mobile.instantstore

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import ufrpe.mobile.instantstore.fragment.FragmentHome

class LoginActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private var idToken = "y0Qf8kJtSzjfQRf2qNUvPaQs"
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       mAuth = FirebaseAuth.getInstance()
       mAuthListener = FirebaseAuth.AuthStateListener {  }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(idToken)
            .requestEmail()
            .build()


        setupUI()

    }


    private fun setupUI() {
        google_button.setOnClickListener {
            signInGoogle()
        }
    }

    fun goToSignUpActvity(view : View){
        val intent = Intent(applicationContext,SignUpActivity::class.java)
        // intent.putExtra("input",editText.text.toString())
        startActivity(intent)
    }

    fun goToMainScreenActvity(view : View){
        val intent = Intent(applicationContext,MainScreenActivity::class.java)
        // intent.putExtra("input",editText.text.toString())
        startActivity(intent)
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun signIn(view : View) {

        if(txtemail.text.toString() != null && txtemail.text.toString() != ""
            && txtpass.text.toString() != null && txtpass.text.toString() != "" ) {
            mAuth!!.signInWithEmailAndPassword(txtemail.text.toString(), txtpass.text.toString())

                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(applicationContext, MainScreenActivity::class.java)
                        startActivity(intent)
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
                }

        }else {
            Toast.makeText(applicationContext, "Insert a valid email and password", Toast.LENGTH_LONG).show()
        }


    }


    override fun onStart() {
        super.onStart()

        val currentUser = mAuth?.currentUser
        Log.i( "FireBase", "Email:" + currentUser?.email)


    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                //Log.w(TAG, "Google sign in failed", e)
                // ...
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(idToken)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth?.signInWithCredential(credential)?.addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(FragmentHome.getLaunchIntent(this))
            } else {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }





}


