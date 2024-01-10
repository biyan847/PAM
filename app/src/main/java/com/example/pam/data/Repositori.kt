package com.example.pam.data

import android.content.ContentValues
import android.util.Log
import com.example.pam.Model.Makanan
import com.example.pam.Model.Pelanggan
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface MakananRepository {
    fun getAll(): Flow<List<Makanan>>
    suspend fun save(makanan: Makanan): String
    suspend fun update(makanan: Makanan)
    suspend fun delete(makananId: String)
    fun getMakananById(makananId: String): Flow<Makanan>
}

class MakananRepositoryImpl(private val firestore: FirebaseFirestore) : MakananRepository {
    override fun getAll(): Flow<List<Makanan>> = flow {
        val snapshot = firestore.collection("Makanan")
            .orderBy("namamkn", Query.Direction.ASCENDING)
            .get()
            .await()
        val makanan = snapshot.toObjects(Makanan::class.java)
        emit(makanan)
    }.flowOn(Dispatchers.IO)


    override suspend fun save(makanan: Makanan): String {
        return try {
            val documentReference = firestore.collection("Makanan").add(makanan).await()
            // Update the Makanan with the Firestore-generated DocumentReference
            firestore.collection("Makanan").document(documentReference.id)
                .set(makanan.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(makanan: Makanan) {
        firestore.collection("Makanan").document(makanan.id).set(makanan).await()
    }

    override suspend fun delete(makananId: String) {
        firestore.collection("Makanan").document(makananId).delete().await()
    }

    override fun getMakananById(makananId: String): Flow<Makanan> {
        return flow {
            val snapshot = firestore.collection("Makanan").document(makananId).get().await()
            val makanan = snapshot.toObject(Makanan::class.java)
            emit(makanan!!)
        }.flowOn(Dispatchers.IO)
    }

}


interface PelangganRepository {
    fun getAll(): Flow<List<Pelanggan>>
    suspend fun save(pelanggan: Pelanggan): String
    fun getPelangganById(pelangganId: String): Flow<Pelanggan>
}

class PelangganRepositoryImpl(private val firestore: FirebaseFirestore) : PelangganRepository{
    override fun getAll(): Flow<List<Pelanggan>> = flow {
        val snapshot = firestore.collection("Pelanggan")
            .orderBy("pelanggan", Query.Direction.ASCENDING)
            .get()
            .await()
        val pelanggan = snapshot.toObjects(Pelanggan::class.java)
        emit(pelanggan)
    }.flowOn(Dispatchers.IO)

    override suspend fun save(pelanggan: Pelanggan): String {
        return try {
            val documentReference = firestore.collection("pelanggan")
                .add(pelanggan)
                .await()
            firestore.collection("pelanggan").document(documentReference.id)
                .set(pelanggan.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }
    override fun getPelangganById(pelangganId: String): Flow<Pelanggan> {
        return flow {
            val snapshot = firestore.collection("Pelanggan").document(pelangganId).get().await()
            val pelanggan = snapshot.toObject(Pelanggan::class.java)
            emit(pelanggan!!)
        }.flowOn(Dispatchers.IO)
    }


}