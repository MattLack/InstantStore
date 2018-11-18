package com.instantstore.instant_store.feature_app

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.app_bar_drawer.*

class MainScreenActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        // Set the toolbar as support action bar
        setSupportActionBar(toolbar)

        init()
    }


    private fun init(){
        val toogle = ActionBarDrawerToggle(Activity(),drawer_layout,toolbar,R.string.nav_open,R.string.nav_close)
        drawer_layout.addDrawerListener(toogle)
        toogle.syncState()
        navigation_view.setNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_settings -> {
                message.text = "set"
            }
            R.id.action_about_us -> {
                message.text = "about"
            }
            R.id.action_messages -> {
                message.text = "message"
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true

    }



}
