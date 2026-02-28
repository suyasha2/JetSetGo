package com.example.travel.repo

import com.example.travel.model.BookingModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class BookingRepoImpl : BookingRepo {
    private val database = FirebaseDatabase.getInstance().getReference("bookings")
    private val auth = FirebaseAuth.getInstance()

    override fun confirmBooking(booking: BookingModel, callback: (Boolean, String) -> Unit) {
        val bookingId = database.push().key ?: return callback(false, "Database Error")
        val uId = auth.currentUser?.uid ?: "anonymous"

        val finalBooking = booking.copy(id = bookingId, currentUserId = uId)

        database.child(bookingId).setValue(finalBooking)
            .addOnSuccessListener { callback(true, "Success") }
            .addOnFailureListener { callback(false, it.message ?: "Error") }
    }
}