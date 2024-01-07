package com.example.pam.data

import android.content.ContentValues
import android.util.Log
import com.example.pam.Model.Admin
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface AdminRepository {
    fun getAll(): Flow<List<Admin>>
    suspend fun save(admin: Admin): String
    suspend fun update(admin: Admin)
    suspend fun delete(adminId: String)
    fun getAdminById(adminId: String): Flow<Admin>
}

class AdminRepositoryImpl(private val firestore: FirebaseFirestore) : AdminRepository {
    override fun getAll(): Flow<List<Admin>> = flow {
        val snapshot = firestore.collection("Admin")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val admin = snapshot.toObjects(Admin::class.java)
        emit(admin)
    }.flowOn(Dispatchers.IO)


    override suspend fun save(admin: Admin): String {
        return try {
            val documentReference = firestore.collection("Admin").add(admin).await()
            // Update the Admin with the Firestore-generated DocumentReference
            firestore.collection("Admin").document(documentReference.id)
                .set(admin.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(admin: Admin) {
        firestore.collection("Admin").document(admin.id).set(admin).await()
    }

    override suspend fun delete(AdminId: String) {
        firestore.collection("Admin").document(AdminId).delete().await()
    }

    override fun getAdminById(AdminId: String): Flow<Admin> {
        return flow {
            val snapshot = firestore.collection("Admin").document(AdminId).get().await()
            val Admin = snapshot.toObject(Admin::class.java)
            emit(Admin!!)
        }.flowOn(Dispatchers.IO)
    }

}