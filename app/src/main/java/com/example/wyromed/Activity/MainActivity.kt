package com.example.wyromed.Activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.wyromed.Fragment.Booking1Fragment
import com.example.wyromed.Fragment.HomeFragment
import com.example.wyromed.Fragment.InboxFragment
import com.example.wyromed.Fragment.ProfileFragment
import com.example.wyromed.R
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity : BaseActivity(){
    private val TAG = MainActivity::class.java.simpleName

    var fragment: Fragment = Fragment()
    val profileFragment: ProfileFragment = ProfileFragment()
    val homeFragment: HomeFragment = HomeFragment()
    val inboxFragment: InboxFragment = InboxFragment()
    val formDetailFragment: Booking1Fragment = Booking1Fragment()
    var botnav: ChipNavigationBar? = null
    var fragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Check Session
        cekSession(this@MainActivity)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //INIT VIEW
        botnav = findViewById(R.id.botnav)
        homeFragment.arguments = bundle

        if (savedInstanceState == null) {
            botnav?.setItemSelected(R.id.home, true)
            fragmentManager = supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, homeFragment)
                ?.commit()
        }

        botnav?.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(i: Int) {
                //Bind Data to Fragment
                inboxFragment.arguments = bundle
                profileFragment.arguments = bundle

                when (i) {
                    R.id.home -> fragment = homeFragment
                    R.id.inbox -> fragment = inboxFragment
                    R.id.profile -> fragment = profileFragment
                }
                if (fragment != null) {
                    fragmentManager = supportFragmentManager
                    fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fragment)
                        ?.commit()
                } else {
                    Log.e("MainActivity", "Error in creating fragment")
                }
            }
        })
    }
}