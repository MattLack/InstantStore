package ufrpe.mobile.instantstore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       mAuth = FirebaseAuth.getInstance()
       mAuthListener = FirebaseAuth.AuthStateListener {  }
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


    fun signIn(view : View) {

        mAuth!!.signInWithEmailAndPassword(txtemail.text.toString(),txtpass.text.toString())

            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(applicationContext, MainScreenActivity::class.java)
                    startActivity(intent)
                }
            }

            .addOnFailureListener { exception ->
                Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }

    }


    override fun onStart() {
        super.onStart()

        val currentUser = mAuth?.currentUser
        Log.i( "FireBase", "Email:" + currentUser?.email)

    }
}
