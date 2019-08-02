package com.maxot.contactbook.ui.window.contact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxot.contactbook.R
import com.maxot.contactbook.constant.Constant
import com.maxot.contactbook.constant.ContactType
import com.maxot.contactbook.data.db.entity.Contact
import com.maxot.contactbook.databinding.FragmentContactBinding
import com.maxot.contactbook.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_edit_contact.*


class ContactFragment : BaseFragment<ContactViewModel, FragmentContactBinding>(){

    lateinit var emailAdapter: PhoneEmailRecycleAdapter
    lateinit var phoneAdapter: PhoneEmailRecycleAdapter

    var interactionHandler = InteractionHandler()
    var id: Long? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.handler = interactionHandler

        id = arguments?.getLong(Constant.ID)

        mViewModel.getContact(preferenceHelper.getEmail()!!, id!!)

        mViewModel.contact.observe(this, Observer {
            mViewDataBinding.data = it

            emailAdapter = PhoneEmailRecycleAdapter(ArrayList(it.emails), ContactType.EMAIL, interactionHandler)
            mViewDataBinding.recyclerEmails.layoutManager = LinearLayoutManager(context)
            mViewDataBinding.recyclerEmails.adapter = emailAdapter


            phoneAdapter = PhoneEmailRecycleAdapter(ArrayList(it.phones), ContactType.PHONE, interactionHandler)
            mViewDataBinding.recyclerPhones.layoutManager = LinearLayoutManager(context)
            mViewDataBinding.recyclerPhones.adapter = phoneAdapter
        })

        mViewModel.deleteContact.observe(this, Observer {
            setToast(it)
            navController.navigate(R.id.contactsFragment)
        })

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_contact
    }

    override fun getViewModel(): ContactViewModel {
        mViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    inner class InteractionHandler {

        fun onEditClick(id: Long) {
            val bundle = Bundle()
            bundle.putLong(Constant.ID, id)
            navController.navigate(R.id.editContactFragment, bundle)
        }

        fun onCallOrEmailClick(contactType: ContactType, data: String){
            when (contactType){
                ContactType.PHONE -> {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:$data")
                    startActivity(intent)
                }
                ContactType.EMAIL -> {
                    val emailIntent = Intent(Intent.ACTION_SENDTO)
                    emailIntent.data = Uri.parse("mailto:$data")
                    startActivity(emailIntent)
                }
            }
        }


        fun onDeleteClick(contact: Contact){
            mViewModel.deleteContact(contact)
        }
    }

}