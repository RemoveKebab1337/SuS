package com.example.kebab

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kebab.databinding.ActivityMenuBinding
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    private var mAuth: FirebaseAuth? = null
    private var nick: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        binding= ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toPC.setOnClickListener{
            updateUI()
            startActivity(Intent(this, PersonalCabinetActivity::class.java))
        }
        binding.toChatButton.setOnClickListener{
            val nick = intent.getStringExtra("Name")
            val j = Intent(this, ChatActivity::class.java)
            j.putExtra("Name", nick)
            startActivity(j)
        }
        binding.logout.setOnClickListener{
            mAuth?.signOut()
            finish()
        }
        initialise()
    }
    private fun updateUI() {
        val intent = Intent(this@MenuActivity, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    private fun initialise() {
        mAuth = FirebaseAuth.getInstance()
    }
}