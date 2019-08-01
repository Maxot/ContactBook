package com.maxot.contactbook

import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.maxot.contactbook.databinding.ActivityMainBinding
import com.maxot.contactbook.ui.base.BaseActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private lateinit var navController: NavController
    lateinit var mAppBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Binding ViewModel
        mViewDataBinding.viewModel = getViewModel()
        setSupportActionBar(toolbar)
        navController = findNavController(R.id.navFragment)

        mAppBarConfiguration = AppBarConfiguration.Builder(setOf(R.id.loginFragment))
                .build()
        setupActionBarWithNavController(navController)

    }

    override fun onBackPressed() {
//        super.onBackPressed()
        navController.popBackStack()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel

    }

}
