package com.example.kebab

import android.R.attr.data
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore


class PersonalCabinetActivity : AppCompatActivity() {
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null
    private var tvEmail: TextView? = null
    private var tvEmailVerifiied: TextView? = null
    private var email: Any? = null
    private var textViewBebra: TextView? = null
    val db = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_cabinet)
        initialise()
        givemydata()
    }

    private fun initialise() {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        tvEmail = findViewById<View>(R.id.tv_email) as TextView
        tvEmailVerifiied = findViewById<View>(R.id.tv_email_verifiied) as TextView
        textViewBebra = findViewById<View>(R.id.textViewBebra) as TextView
    }

    override fun onStart() {
        super.onStart()
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
        email = mUser.email
        tvEmail!!.text = mUser.email
        tvEmailVerifiied!!.text = mUser.isEmailVerified.toString()
        val textViewBebra = findViewById<View>(R.id.textViewBebra) as TextView
    }

    fun toMenu(view: View) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .get()
            .addOnCompleteListener {
                val nick: StringBuffer = StringBuffer()
                if (it.isSuccessful) {
                    for (document in it.result!!) {
                        if (document.id == email) {
                            nick.append(document.data.getValue("$email first")).append(" ")
                                .append(document.data.getValue("$email last"))
                        }
                    }
                    val i = Intent(this, MenuActivity::class.java)
                    i.putExtra("Name", nick.toString())
                    startActivity(i)
                }
            }
    }

    fun givemydata() {
        db.collection("users")
            .get()
            .addOnCompleteListener {
                val nick: StringBuffer = StringBuffer()
                if (it.isSuccessful) {
                    for (document in it.result!!) {
                        if (document.id == email) {
                            nick.append(document.data.getValue("$email first")).append(" ")
                                .append(document.data.getValue("$email last"))
                        }
                    }
                    textViewBebra?.setText(nick)
                }
            }
    }
}