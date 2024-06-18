package com.example.kursach

import Tovars
import TovarsAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: TovarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        recycler = findViewById(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

        adapter = TovarsAdapter(arrayListOf<Tovars>())

        getHotelsData()

        val exitaccBtnHomeAct : Button= findViewById(R.id.exitAccBtn_HomeAct)
        val authHomeAct = FirebaseAuth.getInstance()


        exitaccBtnHomeAct.setOnClickListener {
            authHomeAct.signOut()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        val deleteHotelsBtn: FloatingActionButton = findViewById(R.id.deleteHotelsBtn)
        val addHotelsBtn : FloatingActionButton = findViewById(R.id.add_Hotel_Btn)

        val ref = FirebaseDatabase.getInstance().getReference()


        addHotelsBtn.setOnClickListener{
            val intent = Intent(applicationContext, addTovar::class.java)
            startActivity(intent)
        }
        deleteHotelsBtn.setOnClickListener{
            val intent  = Intent(applicationContext, delet_tovar:: class.java)
            startActivity(intent)
        }
        val profileBtn : Button = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener{
            val intent = Intent(applicationContext, lich_kabinet::class.java)
            startActivity(intent)
        }





    }

    private fun getHotelsData() {
        dbref = FirebaseDatabase.getInstance().getReference("Tovar")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(hotelSnapsot in snapshot.children){
                        val hotels = hotelSnapsot.getValue(Tovars::class.java)

                        adapter.tovarList.add(hotels!!)

                    }
                }

                recycler.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}