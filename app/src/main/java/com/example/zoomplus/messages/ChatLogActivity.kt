package com.example.zoomplus.messages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.zoomplus.R
import com.example.zoomplus.User
import com.example.zoomplus.models.ChatMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*

class ChatLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        supportActionBar?.title = user?.username

        setupDummyData()

        send_button_chat_log.setOnClickListener {
            Log.d(TAG, "send messagge")
            performSendMessage()
        }

    }
    private fun performSendMessage()
    {
        val text = edittext_chat_log.text.toString()
        val fromId = FirebaseAuth.getInstance().uid
        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        val toId = user?.uid
        val reference = FirebaseDatabase.getInstance().getReference("/messages").push()

        val chatMessage = ChatMessage(reference.key!!,text,fromId!!,toId!!,System.currentTimeMillis()/1000)
        reference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "SAVE MSG: ${reference.key}")
            }
    }

    private fun setupDummyData()
    {
        val adapter = GroupAdapter<GroupieViewHolder>()

        adapter.add(ChatFromItem("From meeeessage"))
        adapter.add(ChatToItem("To message"))
        adapter.add(ChatFromItem("From meeeessage"))
        adapter.add(ChatToItem("To message"))
        adapter.add(ChatFromItem("From meeeessage"))
        adapter.add(ChatToItem("To message"))
        adapter.add(ChatFromItem("From meeeessage"))
        adapter.add(ChatToItem("To message"))

        recyclerview_chat_log.adapter = adapter
    }
}

class ChatFromItem(val text:String): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textview_from_row.text = text
    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }
}

class ChatToItem(val text:String): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textview_to_row.text = text
    }

    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }
}
