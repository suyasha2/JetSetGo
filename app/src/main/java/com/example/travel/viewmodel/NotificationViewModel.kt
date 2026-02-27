package com.example.travel.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.travel.model.NotificationModel
import com.google.firebase.database.*

class NotificationViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance().getReference("notifications")
    val notifications = mutableStateListOf<NotificationModel>()

    init {
        fetchNotifications()
    }

    private fun fetchNotifications() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                notifications.clear()
                for (data in snapshot.children) {
                    val item = data.getValue(NotificationModel::class.java)
                    item?.let { notifications.add(it) }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun deleteNotification(id: String) {
        database.child(id).removeValue()
    }
}