package ufrpe.mobile.instantstore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_sign_up.*
import ufrpe.mobile.instantstore.fragment.FragmentHome


class SignUpActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener { }

        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)

        val signbtnGoogle = findViewById<View>(R.id.btncadastr) as SignInButton
        signbtnGoogle.setOnClickListener { view: View? ->
            signUPGoogle()
        }

    }

    private fun signUPGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            // Google Sign In was successful, authenticate with Firebase
            val account = completedTask.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account!!)
        } catch (e: ApiException) {
            // Google Sign In failed, update UI appropriately
            Toast.makeText(this, "Google sign up failed:(", Toast.LENGTH_LONG).show()
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth?.signInWithCredential(credential)?.addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(FragmentHome.getLaunchIntent(this))
            } else {
                Toast.makeText(this, "Google sign up failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }


    fun signUp(view: View) {

        if (txtemail.text.toString() != null && txtemail.text.toString() != ""
            && txtpass.text.toString() != null && txtpass.text.toString() != ""
        ) {

            if (txtpass.text.toString() == txtconfirmpass.text.toString()) {
                mAuth!!.createUserWithEmailAndPassword(txtemail.text.toString(), txtpass.text.toString())

                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, "User Created", Toast.LENGTH_LONG).show()
                            val intent = Intent(applicationContext, MainScreenActivity::class.java)
                            startActivity(intent)
                        }
                    }

                    .addOnFailureListener { exception ->
                        if (exception != null) {
                            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(applicationContext, "Password not match", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(applicationContext, "Insert correctly inputs", Toast.LENGTH_LONG).show()
        }


    }

}
