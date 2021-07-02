package com.nikita.contactfetch.AllActivity


import android.content.ContentProviderOperation
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nikita.contactfetch.R
import kotlinx.android.synthetic.main.add_contact.*


class AddContactActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_contact)
    }
    fun addContact(view: View) {
        val name: String = etName.text.toString()
        val phone = etNumber.text.toString()
        if(TextUtils.isEmpty(name)){
           etName.setError("Please enter name")

        }
        else if(TextUtils.isEmpty(phone)){
         etNumber.setError("please enter phone number")
        }
        else {

            val contact = ArrayList<ContentProviderOperation>()
            contact.add(
                ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    .build()
            )
            // Add name
            contact.add(
                ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0)
                    .withValue(
                        ContactsContract.RawContacts.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
                    )
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name)
                    .build()
            )
            // Contact No Mobile
            contact.add(
                ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0)
                    .withValue(
                        ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                    )
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
                    .withValue(
                        ContactsContract.CommonDataKinds.Phone.TYPE,
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
                    )
                    .build()
            )
            try {
                val results = contentResolver.applyBatch(ContactsContract.AUTHORITY, contact)
                Toast.makeText(this, "Contact Added Sucesfully", Toast.LENGTH_LONG).show()
                val i = Intent(this, ContactListActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}