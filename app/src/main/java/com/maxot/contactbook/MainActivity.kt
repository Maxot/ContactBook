package com.maxot.contactbook

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.maxot.contactbook.databinding.ActivityMainBinding
import com.maxot.contactbook.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        navController = findNavController(R.id.navFragment)

        setupActionBarWithNavController(navController)

    }

    override fun onBackPressed() {
//        super.onBackPressed()
        navController.popBackStack()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        return mViewModel
    }
}
