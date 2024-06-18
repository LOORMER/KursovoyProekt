package com.example.kursach

import Tovars
import TovarsAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class lich_kabinet : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var recycler: RecyclerView
   private lateinit var adapter: TovarsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lich_kabinet)
        recycler = findViewById(R.id.useHotels)
       recycler.layoutManager = LinearLayoutManager(this)
       recycler.setHasFixedSize(true)

       adapter = TovarsAdapter(arrayListOf<Tovars>())

       getHotelsData()



   }
    private fun getHotelsData() {
        dbref = FirebaseDatabase.getInstance().getReference("Hotels")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val filteredHotels = mutableListOf<Tovars>()
                if(snapshot.exists()){
                    for(hotelSnapshot in snapshot.children){
                        val hotel = hotelSnapshot.getValue(Tovars::class.java)
                        if(hotel != null && hotel.byTicket){
                            filteredHotels.add(hotel)
                        }
                    }
                }
                adapter = TovarsAdapter(filteredHotels)
                recycler.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Обработка ошибок
            }
        })
    }
}