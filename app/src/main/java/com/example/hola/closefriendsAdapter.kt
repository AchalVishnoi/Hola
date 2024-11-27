package com.example.hola

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class closefriendsAdapter(private val closeFriendsList: List<closefriendsData>) :
    RecyclerView.Adapter<closefriendsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImageView: ImageView = itemView.findViewById(R.id.imageViewCloseFriends)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val selectCheckBox: CheckBox = itemView.findViewById(R.id.selectCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_close_friends, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = closeFriendsList[position]
        holder.profileImageView.setImageResource(friend.profileImageRes)
        holder.nameTextView.text = friend.name
        holder.selectCheckBox.isChecked = friend.isSelected

        // Update selection state
        holder.selectCheckBox.setOnCheckedChangeListener { _, isChecked ->
            friend.isSelected = isChecked
        }
    }

    override fun getItemCount(): Int = closeFriendsList.size
}