package com.instantstore.instant_store.feature_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //call tollbar menu
        return super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_settings -> {
                text_view.text = "set"
                return true
            }
            R.id.action_about_us -> {
                text_view.text = "about"
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
