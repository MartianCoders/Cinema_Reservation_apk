package com.example.rezervarecinemaiss.RezervationActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.rezervarecinemaiss.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val value = intent.extras!!.getString("user")
    }
}