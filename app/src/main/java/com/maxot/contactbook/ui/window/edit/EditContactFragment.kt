package com.maxot.contactbook.ui.window.edit

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxot.contactbook.BR
import com.maxot.contactbook.R
import com.maxot.contactbook.constant.Constant
import com.maxot.contactbook.constant.ContactType
import com.maxot.contactbook.data.db.entity.Contact
import com.maxot.contactbook.databinding.FragmentEditContactBinding
import com.maxot.contactbook.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_edit_contact.*
import kotlinx.android.synthetic.main.item_input_data.view.*
import kotlin.collections.ArrayList

class EditContactFragment : BaseFragment<EditContactViewModel, FragmentEditContactBinding>() {

    private val interactionHandler = InteractionHandler()
    lateinit var emailAdapter: PhoneEmailEditRecycleAdapter
    lateinit var phoneAdapter: PhoneEmailEditRecycleAdapter

    var id: Long? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.handler = interactionHandler
        id = arguments?.getLong(Constant.ID)

        if (id == -1L || id == null) {
            id = null
            emailAdapter = PhoneEmailEditRecycleAdapter(arrayListOf(""), ContactType.EMAIL)
            recycler_emails.layoutManager = LinearLayoutManager(context)
            recycler_emails.adapter = emailAdapter


            phoneAdapter = PhoneEmailEditRecycleAdapter(arrayListOf(""), ContactType.PHONE)
            recycler_phones.layoutManager = LinearLayoutManager(context)
            recycler_phones.adapter = phoneAdapter
        } else {
            mViewModel.getContact(preferenceHelper.getEmail()!!, id!!)

            mViewModel.contact.observe(this, Observer {
                mViewDataBinding.data = it

                emailAdapter = PhoneEmailEditRecycleAdapter(ArrayList(it.emails), ContactType.EMAIL)
                recycler_emails.layoutManager = LinearLayoutManager(context)
                recycler_emails.adapter = emailAdapter


                phoneAdapter = PhoneEmailEditRecycleAdapter(ArrayList(it.phones), ContactType.PHONE)
                recycler_phones.layoutManager = LinearLayoutManager(context)
                recycler_phones.adapter = phoneAdapter
            })
        }

        mViewModel.insertStatus.observe(this, Observer {
            setToast(it)
            navController.navigate(R.id.contactsFragment)
        })

    }


    fun saveData() {
        val name = et_name.text.toString()
        val secondName = et_last_name.text.toString()

        val emails = arrayListOf<String>()
        for (i in 0..emailAdapter.getSize()) {
            emails.add((recycler_emails.layoutManager?.findViewByPosition(i) as View).edit_text.text.toString())
        }

        val phones = arrayListOf<String>()
        for (i in 0..phoneAdapter.getSize()) {
            phones.add((recycler_phones.layoutManager?.findViewByPosition(i) as View).edit_text.text.toString())
        }

        val contact = Contact(
            id = id,
            firstName = name,
            secondName = secondName,
            owner = preferenceHelper.getEmail()!!,
            emails = emails,
            phones = phones
        )
        mViewModel.insertContact(contact)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_edit_contact
    }

    override fun getViewModel(): EditContactViewModel {
        mViewModel = ViewModelProviders.of(this).get(EditContactViewModel::class.java)
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    inner class InteractionHandler {
        fun onSaveClick(){
            saveData()
        }

        fun onNewPhoneClick(){
            phoneAdapter.addItem()
        }

        fun onNewEmailClick(){
            emailAdapter.addItem()
        }
    }

}