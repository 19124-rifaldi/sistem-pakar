package com.example.sistempakarikannila.model.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import com.example.sistempakarikannila.utils.Result

class GejalaRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : GejalaRepository {
    override suspend fun getGejala(result: (Result<List<String>>) -> Unit) {
        val gejalaList = mutableListOf<String>()
        firebaseDatabase.getReference("gejala")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (gejalaSnapshot in snapshot.children){
                        val gejala = gejalaSnapshot.value.toString()
                        gejalaList.add(gejala)
                        result.invoke(
                            Result.Success(gejalaList)
                        )
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    result.invoke(
                        Result.Failure(error.toString())
                    )
                }

            })
    }
}