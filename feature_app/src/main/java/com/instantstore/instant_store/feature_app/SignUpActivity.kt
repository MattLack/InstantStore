package com.instantstore.instant_store.feature_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun goToMainScreenActvity(view : View){
        val intent = Intent(applicationContext,MainScreenActivity::class.java)
        // intent.putExtra("input",editText.text.toString())
        startActivity(intent)
    }
}
