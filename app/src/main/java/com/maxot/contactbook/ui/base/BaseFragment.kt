package com.maxot.contactbook.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.maxot.contactbook.data.pref.AppPreferenceHelper


abstract class BaseFragment<T: BaseViewModel, V : ViewDataBinding> : androidx.fragment.app.Fragment(){


    lateinit var mViewDataBinding: V
    lateinit var mViewModel: T
    lateinit var navController : NavController
    lateinit var preferenceHelper : AppPreferenceHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //navigation
        navController = NavHostFragment.findNavController(this)
        preferenceHelper = AppPreferenceHelper(context!!)

        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mViewDataBinding.setVariable(getBindingVariable(), getViewModel())
//        mViewDataBinding.executePendingBindings()
        mViewModel = getViewModel()

        return mViewDataBinding.root
    }



    fun setToast(text: String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

//    fun showProgressBar(visibility: Boolean){
//        if (visibility){
//            (activity as MainActivity).progress_bar.visibility = View.VISIBLE
//        } else {
//            (activity as MainActivity).progress_bar.visibility = View.GONE
//        }
//    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): T

    abstract fun getBindingVariable(): Int

//    override fun getOptionMenuId(): Int {
//        return R.menu.feed_menu
//    }
}