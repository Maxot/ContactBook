package com.maxot.contactbook.ui.window.edit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxot.contactbook.BR
import com.maxot.contactbook.R
import com.maxot.contactbook.constant.Constant
import com.maxot.contactbook.data.db.entity.Contact
import com.maxot.contactbook.databinding.FragmentEditContactBinding
import com.maxot.contactbook.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.fragment_edit_contact.*
import kotlinx.android.synthetic.main.item_input_data.view.*
import java.util.*
import kotlin.collections.ArrayList

class EditContactFragment : BaseFragment<EditContactViewModel, FragmentEditContactBinding>() {

    lateinit var emailAdapter: StringRecycleAdapter
    lateinit var phoneAdapter: StringRecycleAdapter

    var id: Long? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = arguments?.getLong(Constant.ID)

        if (id == -1L) {
            emailAdapter = StringRecycleAdapter(arrayListOf(""))
            recycler_emails.layoutManager = LinearLayoutManager(context)
            recycler_emails.adapter = emailAdapter


            phoneAdapter = StringRecycleAdapter(arrayListOf(""))
            recycler_phones.layoutManager = LinearLayoutManager(context)
            recycler_phones.adapter = phoneAdapter
        } else {
            mViewModel.getContact(preferenceHelper.getEmail()!!, id!!)

            mViewModel.contact.observe(this, Observer {
                mViewDataBinding.data = it

                emailAdapter = StringRecycleAdapter(ArrayList(it.emails))
                recycler_emails.layoutManager = LinearLayoutManager(context)
                recycler_emails.adapter = emailAdapter


                phoneAdapter = StringRecycleAdapter(ArrayList(it.phones))
                recycler_phones.layoutManager = LinearLayoutManager(context)
                recycler_phones.adapter = phoneAdapter
            })
        }







        btn_add_email.setOnClickListener {
            emailAdapter.addItem()
        }


        btn_new_phone.setOnClickListener {
            phoneAdapter.addItem()
        }

        btn_save.setOnClickListener {
            saveData()
        }


        mViewModel.insertStatus.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
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

        val contact = Contact(firstName = name,
                secondName = secondName,
                owner = preferenceHelper.getEmail()!!,
                emails = emails,
                phones = phones)
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

}