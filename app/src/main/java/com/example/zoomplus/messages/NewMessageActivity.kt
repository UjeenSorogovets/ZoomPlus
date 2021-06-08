package com.example.zoomplus.messages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.zoomplus.R

class NewMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        supportActionBar?.title = "Select user"

        val recyclerview_newmessage = findViewById<RecyclerView>(R.id.recyclerview_newmessage)
        //val adapter = GroupAdapter<RecyclerView.ViewHolder>()
        recyclerview_newmessage.adapter
    }
}

