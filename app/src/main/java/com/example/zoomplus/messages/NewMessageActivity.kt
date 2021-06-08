package com.example.zoomplus.messages

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.zoomplus.R
import com.example.zoomplus.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*
import java.security.AccessController.getContext


val TAG = "MyTagActivity"

class NewMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        supportActionBar?.title = "Select user"

        val recyclerview_newmessage = findViewById<RecyclerView>(R.id.recyclerview_newmessage)

        val adapter = GroupAdapter<GroupieViewHolder>()

        recyclerview_newmessage.adapter = adapter

        fetchUsers()
    }
    private fun fetchUsers(){
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()

                p0.children.forEach {
                    Log.d(TAG, it.toString())
                    val user = it.getValue(User::class.java)
                    if (user != null) {
                        adapter.add(UserItem(user))
                    }

                }
                //val recyclerview_newmessage = findViewById<RecyclerView>(R.id.recyclerview_newmessage)
                recyclerview_newmessage.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}



class UserItem(val user: User): Item<GroupieViewHolder>()
{
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.username_textview_new_message.text = user.username

        val w: Int = 100
        val h: Int = 100

        val conf = Bitmap.Config.ARGB_8888 // see other conf types

        //val bmp = Bitmap.createBitmap(w, h, conf) // this creates a MUTABLE bitmap
        //viewHolder.itemView.imageview_new_message.setImageBitmap(bmp)
        Picasso.get().load("https://www.gstatic.com/webp/gallery/4.sm.jpg")
            .into(viewHolder.itemView.imageview_new_message)
        /*Picasso.get().load(user.profileImageUrl)
                     .into(viewHolder.itemView.imageview_new_message)*/


    }
    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }
}

