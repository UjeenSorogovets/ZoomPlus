package com.example.zoomplus.views
import com.bumptech.glide.Glide
import com.example.zoomplus.R
import com.example.zoomplus.User
import com.example.zoomplus.models.ChatMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupieViewHolder

import com.xwray.groupie.Item

import kotlinx.android.synthetic.main.latest_message_row.view.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*


class LatestMessageRow(val chatMessage: ChatMessage, val addFontSize: Boolean?): Item<GroupieViewHolder>() {
    var chatPartnerUser: User? = null

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.message_textview_latest_message.text = chatMessage.text

        val chatPartnerId: String
        if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
            chatPartnerId = chatMessage.toId
        } else {
            chatPartnerId = chatMessage.fromId
        }

        val currentTextSize = viewHolder.itemView.username_textview_latest_message.textSize
        if (addFontSize!=null)
        {
            if (addFontSize==true)
            {
                viewHolder.itemView.username_textview_latest_message.textSize=25f
                viewHolder.itemView.message_textview_latest_message.textSize=20f
            }
            else if (addFontSize==false)
            {
                viewHolder.itemView.username_textview_latest_message.textSize=20f
                viewHolder.itemView.message_textview_latest_message.textSize=15f
            }
        }

        val ref = FirebaseDatabase.getInstance().getReference("/users/$chatPartnerId")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                chatPartnerUser = p0.getValue(User::class.java)
                viewHolder.itemView.username_textview_latest_message.text = chatPartnerUser?.username


                val targetImageView = viewHolder.itemView.imageview_latest_message

                Glide.with(viewHolder.itemView).load(chatPartnerUser?.profileImageUrl).into(targetImageView)
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    override fun getLayout(): Int {
        return R.layout.latest_message_row
    }
}