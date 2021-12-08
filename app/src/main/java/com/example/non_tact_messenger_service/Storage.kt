package com.example.non_tact_messenger_service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.NullPointerException
import java.util.*


object Storage{
    private val storageInstance: FirebaseStorage by lazy { FirebaseStorage.getInstance() }

    private val currentUserRef = storageInstance.reference
        .child("eurPdsswDs3rMG35hqM") // 임시로 사용자 이름을 넣어줌


//        get() = storageInstance.reference
//            .child(FirebaseAuth.getInstance().currentUser?.uid
//                ?: throw NullPointerException("UID is null."))


    fun uploadMessageImage(imageBytes: ByteArray,
                           onSuccess: (imagePath: String) -> Unit) {
        val ref = currentUserRef.child("messages/${UUID.nameUUIDFromBytes(imageBytes)}") // 어센틱케이션으로 받은 이미지를 stroage에 넣어줌
        ref.putBytes(imageBytes)
            .addOnSuccessListener {
                onSuccess(ref.path)
            }
    }

    fun pathToReference(path: String) = storageInstance.getReference(path)
}