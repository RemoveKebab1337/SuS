package com.example.kebab

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kebab.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatBinding
    private var mAuth: FirebaseAuth? = null
    private var msg: String? = null
    private var etMsg: EditText? = null
    val db = Firebase.firestore
    private var textViewResult: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sendButton.setOnClickListener {
            AddToFirestore()
        }
        ReadFromFirestore()
        initialise()
    }

    private fun AddToFirestore() {
        msg = etMsg?.text.toString()
        val currentDate = Date()
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val dateText = dateFormat.format(currentDate)
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val timeText = timeFormat.format(currentDate) + " " + dateText
        val nick = intent.getStringExtra("Name")
        val msgs = hashMapOf(
            "$nick в $timeText сказал: " to msg,
        )
        db.collection("messages").document("messagesHistory")
            .set(msgs)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }


    }

    private fun ReadFromFirestore() {
        val docRef = db.collection("messages").document("messagesHistory")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    textViewResult?.append("${document.data}")
                } else {
                }
            }

    }

    private fun initialise() {
        mAuth = FirebaseAuth.getInstance()
        etMsg = findViewById<View>(R.id.poleVvoda) as EditText
        textViewResult = findViewById(R.id.textViewResult) as TextView
    }

}