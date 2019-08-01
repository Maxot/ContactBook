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

abstract class BaseDialogFragment<T: BaseViewModel, V : ViewDataBinding> : androidx.fragment.app.DialogFragment(){

    lateinit var mViewDataBinding: V
    lateinit var mViewModel: T
    lateinit var navController : NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //navigation
        navController = NavHostFragment.findNavController(this)

        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mViewDataBinding.setVariable(getBindingVariable(), getViewModel())
        mViewDataBinding.executePendingBindings()
        mViewModel = getViewModel()

        return mViewDataBinding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.invalidateOptionsMenu()
    }

//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        if (context as Activity is ToolbarableActivity){
//            // context.setTitle(getTitle())
//        }
//    }
//
//    override fun onAttach(activity: Activity?) {
//        super.onAttach(activity)
//        if (activity is ToolbarableActivity){
//            activity.setTitle(getTitle())
//        }
//    }


    fun setToast(text: String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): T

    abstract fun getBindingVariable(): Int

//    override fun getOptionMenuId(): Int {
//        return R.menu.feed_menu
//    }
}