package ufrpe.mobile.instantstore

import android.app.ListActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class SignUpActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }
    }

    fun goToMainScreenActvity(view : View){
        val intent = Intent(applicationContext,MainScreenActivity::class.java)
        // intent.putExtra("input",editText.text.toString())
        startActivity(intent)
    }

/*
    fun signUp(view : View) {

        mAuth!!.createUserWithEmailAndPassword(emailText.text.toString(),passwordText.text.toString())

            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext,"User Created", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext, ListActivity::class.java)
                    startActivity(intent)
                }
            }

            .addOnFailureListener { exception ->
                if (exception != null) {
                    Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }

    }*/

}
