package com.maxot.contactbook.ui.window.contacts


import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.maxot.contactbook.R
import com.maxot.contactbook.constant.Constant
import com.maxot.contactbook.constant.SortType
import com.maxot.contactbook.data.db.entity.Contact
import com.maxot.contactbook.databinding.FragmentContactsBinding
import com.maxot.contactbook.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contacts.*
import java.util.*


class ContactsFragment : BaseFragment<ContactsViewModel, FragmentContactsBinding>() {

    lateinit var data: List<Contact>
    var sortType: SortType? = null

    private lateinit var adapter: ContactsRecycleAdapter
    private var interactionHandler = InteractionHandler()

    private var sortCheckedItem = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (preferenceHelper.getEmail() == null)
            navController.navigate(R.id.loginActivity)

        mViewDataBinding.handler = interactionHandler
        mViewDataBinding.email = preferenceHelper.getEmail()

        mViewModel.getContacts(preferenceHelper.getEmail()!!)

        mViewModel.contacts.observe(this, Observer {
            if (it.isEmpty()) {
                tv_have_not_contacts.visibility = View.VISIBLE
                recyclerContact.visibility = View.GONE
            } else {
                tv_have_not_contacts.visibility = View.GONE
                recyclerContact.visibility = View.VISIBLE

                data = it
                sortType?.let {
                    sortData(it)
                }
                adapter = ContactsRecycleAdapter(it, interactionHandler)
                recyclerContact.layoutManager = LinearLayoutManager(context)
                recyclerContact.adapter = adapter
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.log_out -> {
                preferenceHelper.saveEmail(null)
                googleSignOut()
                navController.navigate(R.id.loginActivity)
            }
            R.id.sort -> {
                showSortDialog()
            }
        }
        return false
    }

    private fun showSortDialog() {
        val listItems = arrayOf("By name", "By name reverse", "By e-mail count", "By phone number count")

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose sort category")

        builder.setSingleChoiceItems(listItems, sortCheckedItem
        ) { _,
            which ->
            run {
                sortCheckedItem = which
                when (which) {
                    0 -> sortType = SortType.BY_NAME
                    1 -> sortType = SortType.BY_NAME_REVERSE
                    2 -> sortType = SortType.BY_EMAIL_COUNT
                    3 -> sortType = SortType.BY_PHONE_COUNT
                }
            }
        }

        builder.setPositiveButton("Done") { dialog, _ ->
            run {
                sortType?.let {
                    sortData(it)
                }
                dialog.dismiss()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }

    private fun sortData(sortType: SortType) {
        when (sortType) {
            SortType.BY_NAME -> {
                Collections.sort(data) { o1, o2 -> (o1.firstName + o1.secondName).compareTo(o2.firstName + o2.secondName) }
                adapter.updateData(data)
            }
            SortType.BY_NAME_REVERSE -> {
                Collections.sort(data) { o1, o2 -> (o2.firstName + o2.secondName).compareTo(o1.firstName + o1.secondName) }
                adapter.updateData(data)
            }
            SortType.BY_EMAIL_COUNT -> {
                Collections.sort(data) { o1, o2 -> o2.emails.size - o1.emails.size }
                adapter.updateData(data)
            }
            SortType.BY_PHONE_COUNT -> {
                Collections.sort(data) { o1, o2 -> o2.phones.size - o1.phones.size }
                adapter.updateData(data)
            }
        }

    }


    private fun googleSignOut() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(activity as Activity, gso)
        mGoogleSignInClient.signOut()
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

        fun onNewContactClick() {
            val bundle = Bundle()
            bundle.putLong(Constant.ID, -1L)
            navController.navigate(R.id.editContactFragment, bundle)
        }
    }

}