package com.nikita.contactfetch.AllActivity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nikita.contactfetch.*
import com.nikita.contactfetch.Adapter.ContactsAdapter
import com.nikita.contactfetch.ContentViewModel.ContactsViewModel
import kotlinx.android.synthetic.main.activity_contacts.*


class ContactListActivity : AppCompatActivity() {
    private val contentModel by viewModels<ContactsViewModel>()
    private val Read_Contact_Permission = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        init()
    }

    private fun init() {
        val adapter = ContactsAdapter(this)
        contact_list.adapter = adapter
        contentModel.contactsLiveData.observe(this, Observer {
            adapter.contacts = it
        })
        if (hasPermission(Manifest.permission.READ_CONTACTS)) {
            contentModel.fetchContacts()
        } else {
            requestPermissionWithRationale(Manifest.permission.READ_CONTACTS, Read_Contact_Permission, getString(
                R.string.contact_permission_rationale
            ))
        }
        btn_addcontact.setOnClickListener(View.OnClickListener {
            if (hasPermission(Manifest.permission.WRITE_CONTACTS)) {
                val intent = Intent(this,AddContactActivity::class.java)
                startActivity(intent)            } else {
                requestPermissionWithRationale(Manifest.permission.WRITE_CONTACTS, Read_Contact_Permission, getString(
                    R.string.contact_permission_rationale
                ))
            }

        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Read_Contact_Permission && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            contentModel.fetchContacts()
        }
    }
}
