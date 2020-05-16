package com.example.apptodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {
    var addToDo = AddToDo()
    var dialogdelete = Delete()
    var fragmentH = HomeFragment()
    lateinit var cvdelete: CongViec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Show fragment
        loadFragment(HomeFragment())
        var actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setLogo(R.mipmap.note_50px)
        actionBar?.setDisplayUseLogoEnabled(true)

        navigationView.setOnNavigationItemSelectedListener { menuItem ->
            when {
                menuItem.itemId == R.id.home -> {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                menuItem.itemId == R.id.important -> {
                    loadFragment(ImportantFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                menuItem.itemId == R.id.done -> {
                    loadFragment(DoneFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
//        fragmentH.onClickDelete = {
//            dialogdelete.congViecdelete =it
//            dialogdelete.show (supportFragmentManager, "delete")
//        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.framelayout, fragment)
            fragmentTransaction.commit()
        }
    }


}


