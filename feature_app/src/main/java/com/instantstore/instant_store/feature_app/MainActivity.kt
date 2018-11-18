package com.instantstore.instant_store.feature_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun goToSignUpActvity(view : View){
        val intent = Intent(applicationContext,SignUpActivity::class.java)
        // intent.putExtra("input",editText.text.toString())
        startActivity(intent)
    }

    fun gotoLoginActivity(view : View){
        val intent = Intent(applicationContext,LoginActivity::class.java)
        // intent.putExtra("input",editText.text.toString())
        startActivity(intent)
    }
}
