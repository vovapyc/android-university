package com.example.bnv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var manager: FragmentManager = supportFragmentManager
    private var fr1: FragmentFirst? = null
    private var fr2: FragmentSecond? = null
    private var fr3: FragmentThird? = null
    private val TAG_FR_1: String = "Fragment1"
    private val TAG_FR_2: String = "Fragment2"
    private val TAG_FR_3: String = "Fragment3"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fr1 = FragmentFirst()
        fr2 = FragmentSecond()
        fr3 = FragmentThird()

        if (savedInstanceState == null) {
            manager.beginTransaction().add(R.id.fragment, fr1!!, TAG_FR_1).commit()
        }


        main_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.action_to_bin -> {
                title_num_in.text = "Переведення числа у двійкову систему числення"

                val fragment = supportFragmentManager.findFragmentByTag(TAG_FR_1) as? FragmentFirst
                if (fragment == null) {
                    manager.beginTransaction().replace(R.id.fragment, fr1!!, TAG_FR_1).commit()
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.action_to_dec -> {
                title_num_in.text = "Переведення двійкового числа у десяткову систему числення"

                val fragment = supportFragmentManager.findFragmentByTag(TAG_FR_2) as? FragmentSecond
                if (fragment == null) {
                    manager.beginTransaction().replace(R.id.fragment, fr2!!, TAG_FR_2).commit()
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.action_add_bins -> {
                title_num_in.text = "Додавання двійкових чисел"
                val fragment = supportFragmentManager.findFragmentByTag(TAG_FR_3) as? FragmentThird
                if (fragment == null) {
                    manager.beginTransaction().replace(R.id.fragment, fr3!!, TAG_FR_3).commit()
                }

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val f = title_num_in.text.toString()
        outState.putString("TITLE", f)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val k = savedInstanceState.getString("TITLE")
        title_num_in.text = k
    }
}

