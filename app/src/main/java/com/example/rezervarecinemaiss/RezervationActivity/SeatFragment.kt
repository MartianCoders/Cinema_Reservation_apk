package com.example.rezervarecinemaiss.RezervationActivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rezervarecinemaiss.Data.Movie
import com.example.rezervarecinemaiss.Data.Reservation
import com.example.rezervarecinemaiss.R
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@AndroidEntryPoint
class SeatFragment: Fragment(), View.OnClickListener {

    private val viewModel by viewModels<ReservationViewModel>()
    private val selectedSeats: MutableList<Int> = ArrayList()
    private val args: SeatFragmentArgs by navArgs()

    lateinit var reservations: List<Reservation>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nextSeatButton: Button = view.findViewById(R.id.next_seat_button)

        val seat11: TextView = view.findViewById(R.id.seat_1_1)
        val seat12: TextView = view.findViewById(R.id.seat_1_2)
        val seat13: TextView = view.findViewById(R.id.seat_1_3)
        val seat14: TextView = view.findViewById(R.id.seat_1_4)
        val seat21: TextView = view.findViewById(R.id.seat_2_1)
        val seat22: TextView = view.findViewById(R.id.seat_2_2)
        val seat23: TextView = view.findViewById(R.id.seat_2_3)
        val seat24: TextView = view.findViewById(R.id.seat_2_4)
        val seat31: TextView = view.findViewById(R.id.seat_3_1)
        val seat32: TextView = view.findViewById(R.id.seat_3_2)
        val seat33: TextView = view.findViewById(R.id.seat_3_3)
        val seat34: TextView = view.findViewById(R.id.seat_3_4)
        val seat41: TextView = view.findViewById(R.id.seat_4_1)
        val seat42: TextView = view.findViewById(R.id.seat_4_2)
        val seat43: TextView = view.findViewById(R.id.seat_4_3)
        val seat44: TextView = view.findViewById(R.id.seat_4_4)
        val seat51: TextView = view.findViewById(R.id.seat_5_1)
        val seat52: TextView = view.findViewById(R.id.seat_5_2)
        val seat53: TextView = view.findViewById(R.id.seat_5_3)
        val seat54: TextView = view.findViewById(R.id.seat_5_4)
        val seat61: TextView = view.findViewById(R.id.seat_6_1)
        val seat62: TextView = view.findViewById(R.id.seat_6_2)
        val seat63: TextView = view.findViewById(R.id.seat_6_3)
        val seat64: TextView = view.findViewById(R.id.seat_6_4)
        val seat71: TextView = view.findViewById(R.id.seat_7_1)
        val seat72: TextView = view.findViewById(R.id.seat_7_2)
        val seat73: TextView = view.findViewById(R.id.seat_7_3)
        val seat74: TextView = view.findViewById(R.id.seat_7_4)
        val seat81: TextView = view.findViewById(R.id.seat_8_1)
        val seat82: TextView = view.findViewById(R.id.seat_8_2)
        val seat83: TextView = view.findViewById(R.id.seat_8_3)
        val seat84: TextView = view.findViewById(R.id.seat_8_4)

        seat11.setOnClickListener(this)
        seat12.setOnClickListener(this)
        seat13.setOnClickListener(this)
        seat14.setOnClickListener(this)
        seat21.setOnClickListener(this)
        seat22.setOnClickListener(this)
        seat23.setOnClickListener(this)
        seat24.setOnClickListener(this)
        seat31.setOnClickListener(this)
        seat32.setOnClickListener(this)
        seat33.setOnClickListener(this)
        seat34.setOnClickListener(this)
        seat41.setOnClickListener(this)
        seat42.setOnClickListener(this)
        seat43.setOnClickListener(this)
        seat44.setOnClickListener(this)
        seat51.setOnClickListener(this)
        seat52.setOnClickListener(this)
        seat53.setOnClickListener(this)
        seat54.setOnClickListener(this)
        seat61.setOnClickListener(this)
        seat62.setOnClickListener(this)
        seat63.setOnClickListener(this)
        seat64.setOnClickListener(this)
        seat71.setOnClickListener(this)
        seat72.setOnClickListener(this)
        seat73.setOnClickListener(this)
        seat74.setOnClickListener(this)
        seat81.setOnClickListener(this)
        seat82.setOnClickListener(this)
        seat83.setOnClickListener(this)
        seat84.setOnClickListener(this)

        subscribeObservers(view)



        nextSeatButton.setOnClickListener {
            if(selectedSeats.isNotEmpty()) {
                val action = SeatFragmentDirections.goToConfirmationSeats(selectedSeats.toString(), args.movieSelected)
                selectedSeats.clear()
                Navigation.findNavController(view).navigate(action)
            } else {
                Toast.makeText(context, "No Seat Selected", Toast.LENGTH_SHORT).show()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        if(v!!.id in selectedSeats) {
            val pressedSeatView: TextView = view!!.findViewById(v.id)
            pressedSeatView.background = resources.getDrawable(R.drawable.seat_free)
            selectedSeats.remove(v!!.id)
            Log.i("_", selectedSeats.toString())
        } else {
            selectedSeats.add(v!!.id)
            Log.i("_", selectedSeats.toString())
            val pressedSeatView: TextView = view!!.findViewById(v.id)
            pressedSeatView.background = resources.getDrawable(R.drawable.seat_selected)
        }
    }

    private fun subscribeObservers(view: View) {
        viewModel.getReservations().observe(this, object: Observer<List<Reservation>> {
            override fun onChanged(t: List<Reservation>?) {
                reservations = t!!

                for(reservation: Reservation in reservations) {
                    if(reservation.movie == args.movieSelected) {
                        val seatIDs =
                            reservation.seats_reservation.splitIgnoreEmpty(",", "[", "]", " ")
                                .toMutableList()
                        for (seat in seatIDs) {
                            val pressedSeatView: TextView = view!!.findViewById(seat.toInt())
                            pressedSeatView.background =
                                resources.getDrawable(R.drawable.seat_ocupied)
                            pressedSeatView.isClickable = false
                        }
                    }
                }
            }
        })
    }

    fun CharSequence.splitIgnoreEmpty(vararg delimiters: String): List<String> {
        return this.split(*delimiters).filter {
            it.isNotEmpty()
        }
    }


}