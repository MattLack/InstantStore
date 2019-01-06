package ufrpe.mobile.instantstore

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.app_bar_drawer.*
import ufrpe.mobile.instantstore.fragment.*


class MainScreenActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    lateinit var fragmentManager: FragmentManager
    private val savedState: Bundle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        // Set the toolbar as support action bar
        setSupportActionBar(toolbar)

        init()


        /*if(fragmentManager.isStateSaved == false){
            val fragment = FragmentHome.newInstance()
            replaceFragment(fragment)
        }*/

    }

    override fun getApplicationContext(): Context {
        return super.getApplicationContext()
    }


    private fun init(){
        //this object will help to replace the framelayout with the fragments
        fragmentManager = supportFragmentManager
        val toogle = ActionBarDrawerToggle(Activity(),drawer_layout,toolbar,R.string.nav_open,R.string.nav_close)
        drawer_layout.addDrawerListener(toogle)
        toogle.syncState()
        navigation_view.setNavigationItemSelectedListener(this)
    }

    /*private fun replaceFragment(fragment: Fragment) {
         val fragmentTransaction = supportFragmentManager.beginTransaction()
         fragmentTransaction.replace(R.id.fragment_main_screen, fragment)
         fragmentTransaction.commit()
     }*/


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_home -> {
//                message.text = "set"
                fragmentManager.beginTransaction().replace(R.id.fragment_main_screen, FragmentHome()).commit()
            }
            R.id.action_settings -> {
//                message.text = "set"
                fragmentManager.beginTransaction().replace(R.id.fragment_main_screen, FragmentSettings()).commit()
            }
            R.id.action_about_us -> {
//                message.text = "about"
                fragmentManager.beginTransaction().replace(R.id.fragment_main_screen, FragmentAbout()).commit()
            }
            R.id.action_demand -> {
//                message.text = "message"
                fragmentManager.beginTransaction().replace(R.id.fragment_main_screen, FragmentDemand()).commit()
            }
            R.id.action_upload -> {
//                message.text = "message"
                fragmentManager.beginTransaction().replace(R.id.fragment_main_screen, FragmentUpload()).commit()
                /*val intent = Intent(applicationContext, UploadActivity::class.java)
                startActivity(intent)*/
            }else -> {
        }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true

    }





}
