package com.example.sistempakarikannila.model.repository

import android.util.Log
import com.example.sistempakarikannila.model.Gejala
import com.example.sistempakarikannila.model.Penyakit
import com.example.sistempakarikannila.model.PenyakitX
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import javax.inject.Inject
import com.example.sistempakarikannila.utils.Result

class PenyakitRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : PenyakitRepository {
    override suspend fun getPenyakit(result: (Result<List<PenyakitX>>)-> Unit) {
        val penyakitList = mutableListOf<PenyakitX>()
        firebaseDatabase.getReference("penyakit")

            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (penyakitSnapshot in snapshot.children){
                        val penyakit = penyakitSnapshot.getValue(Penyakit::class.java)
                        val penyakit2 = penyakitSnapshot.getValue(PenyakitX::class.java)
                        Log.i("penyakit2","$penyakit")

                        penyakitList.add(penyakit2!!)
                        result.invoke(
                            Result.Success(penyakitList.toList())
                        )

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("error cancel","$error")
                    result.invoke(
                        Result.Failure(error.toString())
                    )
                }
            } )
        Log.i("penyakitlist","$penyakitList")

    }
}