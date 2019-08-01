package com.maxot.contactbook.ui.window.contacts


import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxot.contactbook.R
import com.maxot.contactbook.constant.Constant
import com.maxot.contactbook.databinding.FragmentContactsBinding
import com.maxot.contactbook.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contacts.*


class ContactsFragment : BaseFragment<ContactsViewModel, FragmentContactsBinding>() {

    lateinit var adapter: ContactsRecycleAdapter
    var interactionHander = InteractionHandler()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      mViewModel.getContacts(preferenceHelper.getEmail()!!)

        mViewModel.contacts.observe(this, Observer {
            adapter = ContactsRecycleAdapter(it, interactionHander)
            recyclerContact.layoutManager = LinearLayoutManager(context)
            recyclerContact.adapter = adapter
        })


        btn_new_contact.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong(Constant.ID, -1L)
            navController.navigate(R.id.editContactFragment, bundle)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_contacts
    }

    override fun getViewModel(): ContactsViewModel {
        mViewModel = ViewModelProviders.of(this).get(ContactsViewModel::class.java)
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    inner class InteractionHandler {

        fun onContactClick(id: Long) {
            val bundle = Bundle()
            bundle.putLong(Constant.ID, id)
            navController.navigate(R.id.contactFragment, bundle)
        }
    }

}