package com.example.evoid

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.DocumentsContract.getDocumentId
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.WriteBatch
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_registered.*
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.fragment_contacts.view.*
import java.net.URI
import kotlin.reflect.typeOf

class contacts : Fragment() {
    val REQUEST_CONTACT = 1
    lateinit var contactUri: Uri
    var phoneNumber:Long = 0
    var phoneId:String = ""
    var phoneName:String = ""
    var position = 0
    var docId1 = ""
    var docId2 = ""
    var docId3 = ""
    var docId4 = ""
    var docId5 = ""
    private lateinit var auth : FirebaseAuth

    private val mFireStore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        auth = Firebase.auth
        val view:View = inflater!!.inflate(R.layout.fragment_contacts, container, false)
        displayContact()

        view.addContactButton.setOnClickListener{view->
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            if (intent.resolveActivity(requireActivity().packageManager) != null)
            {
                startActivityForResult(intent, REQUEST_CONTACT)
            }


        }
        view.deleteContact1.setOnClickListener {
            position = 1
            deleteContact()
        }
        view.deleteContact2.setOnClickListener {
            position = 2
            deleteContact()
        }
        view.deleteContact3.setOnClickListener {
            position = 3
            deleteContact()
        }
        view.deleteContact4.setOnClickListener {
            position = 4
            deleteContact()
        }
        view.deleteContact5.setOnClickListener {
            position = 5
            deleteContact()
        }

        return view

    }

    private fun deleteContact() {
        if (position == 1) {
            //var batch: WriteBatch = mFireStore.batch()
            //getDocument()
            mFireStore.collection(constants.USERS)
                .document(auth.currentUser!!.uid)
                .collection(constants.ContactsDetails)
                .document(docId1).delete()
            clearContact()
            displayContact()




        }

    }

    private fun clearContact() {
        contactIcon1.visibility = View.INVISIBLE
        deleteContact1.visibility = View.INVISIBLE
        contact1Contacts.visibility = View.INVISIBLE
        contactIcon2.visibility = View.INVISIBLE
        deleteContact2.visibility = View.INVISIBLE
        contact2Contacts.visibility = View.INVISIBLE
        contactIcon3.visibility = View.INVISIBLE
        deleteContact3.visibility = View.INVISIBLE
        contact3Contacts.visibility = View.INVISIBLE
        contactIcon4.visibility = View.INVISIBLE
        deleteContact4.visibility = View.INVISIBLE
        contact4Contacts.visibility = View.INVISIBLE
        contactIcon5.visibility = View.INVISIBLE
        deleteContact5.visibility = View.INVISIBLE
        contact5Contacts.visibility = View.INVISIBLE
        contact1Contacts.text=""
        contact2Contacts.text =""
        contact3Contacts.text=""
        contact4Contacts.text=""
        contact5Contacts.text=""


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK )
        {
            contactUri = data!!.data!!

            getContactId()
            getContactName()
            getContactNumber()
            addContact()
            displayContact()

        }

    }

    private fun displayContact() {

        mFireStore.collection(constants.USERS)
            .document(auth.currentUser!!.uid)
            .collection(constants.ContactsDetails)
            .get()
            .addOnSuccessListener {result->

                for (document in result)
                {
                    if (contact1Contacts.text!=document.get("contactName").toString()
                        && contact2Contacts.text!=document.get("contactName").toString()
                        && contact3Contacts.text!=document.get("contactName").toString()
                        && contact4Contacts.text!=document.get("contactName").toString()
                        && contact5Contacts.text!=document.get("contactName").toString()

                    )
                    {
                        if (contact1Contacts.text.isEmpty())
                        {
                            contactIcon1.visibility = View.VISIBLE
                            deleteContact1.visibility = View.VISIBLE
                            contact1Contacts.visibility = View.VISIBLE
                            contact1Contacts.setText(document.get("contactName").toString())
                            docId1 = document.get("contactId").toString()
                        }
                        else if (contact2Contacts.text.isEmpty())
                        {
                            contactIcon2.visibility = View.VISIBLE
                            deleteContact2.visibility = View.VISIBLE
                            contact2Contacts.visibility = View.VISIBLE
                            contact2Contacts.setText(document.get("contactName").toString())
                            docId2 = document.get("contactId").toString()
                        }
                        else if (contact3Contacts.text.isEmpty())
                        {
                            contactIcon3.visibility = View.VISIBLE
                            deleteContact3.visibility = View.VISIBLE
                            contact3Contacts.visibility = View.VISIBLE
                            contact3Contacts.setText(document.get("contactName").toString())
                            docId3 = document.get("contactId").toString()
                        }
                        else if (contact4Contacts.text.isEmpty())
                        {
                            contactIcon4.visibility = View.VISIBLE
                            deleteContact4.visibility = View.VISIBLE
                            contact4Contacts.visibility = View.VISIBLE
                            contact4Contacts.setText(document.get("contactName").toString())
                            docId4 = document.get("contactId").toString()
                        }
                        else if (contact5Contacts.text.isEmpty())
                        {
                            contactIcon5.visibility = View.VISIBLE
                            deleteContact5.visibility = View.VISIBLE
                            contact5Contacts.visibility = View.VISIBLE
                            contact5Contacts.setText(document.get("contactName").toString())
                            docId5 = document.get("contactId").toString()
                        }
                    }

                }







            }

    }

    private fun addContact() {
        val contactInfo = com.example.evoid.ContactsDetails(phoneId,phoneName,phoneNumber)
        firestoreClass().addContact(contactInfo, phoneId)

    }

    private fun getContactId() {
        val projection3 = arrayOf(ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID)
        val cursor3 = requireActivity().contentResolver.query(contactUri, projection3 , null,null,null)
        if (cursor3!!.moveToNext())
        {
            phoneId = cursor3.getString(cursor3.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID))
            Toast.makeText(requireContext(), "getting", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getContactNumber() {
        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val cursor = requireActivity().contentResolver.query(contactUri, projection , null,null,null)
        if (cursor!!.moveToNext())
        {
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                .replace("+91 ","").replace(" ", "").toLong()
        }
    }

    private fun getContactName() {

        val projection2 = arrayOf(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME_PRIMARY)
        val cursor2 = requireActivity().contentResolver.query(contactUri, projection2 , null,null,null)
        if (cursor2!!.moveToNext())
        {
            phoneName = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME_PRIMARY))




        }




    }




}
