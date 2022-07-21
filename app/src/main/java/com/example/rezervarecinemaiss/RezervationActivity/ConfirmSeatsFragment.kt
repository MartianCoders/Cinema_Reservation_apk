package com.example.rezervarecinemaiss.RezervationActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.rezervarecinemaiss.Data.Reservation
import com.example.rezervarecinemaiss.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmSeatsFragment: Fragment() {

    private val viewModel by viewModels<ReservationViewModel>()
    private val args: ConfirmSeatsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm_seats, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val howManyTextView: TextView = view.findViewById(R.id.how_many_textview)
        val finalPriceTextView: TextView = view.findViewById(R.id.final_price_textview)
        val nameTextView: TextView = view.findViewById(R.id.editText_name)
        val phoneTextView: TextView = view.findViewById(R.id.editText_phone)

        val termsCheckBox: CheckBox = view.findViewById(R.id.terms_check_box)

        val confirmationButton: Button = view.findViewById(R.id.confirm_button)

        val seatIDs = args.seatList.splitIgnoreEmpty(",", "[", "]", " ")
        Log.i("_", seatIDs.toString())

        howManyTextView.text = "Selected " + seatIDs.size + " seats"
        finalPriceTextView.text = (15 * seatIDs.size).toString() + " LEI"

        confirmationButton.setOnClickListener {
            if(nameTextView.text.isNotEmpty() && phoneTextView.text.isNotEmpty()) {
                if(termsCheckBox.isChecked) {
                    val reservation = Reservation(movie= args.movieSelected, name= nameTextView.text.toString(), phone = phoneTextView.text.toString(), seats_reservation = args.seatList)
                    viewModel.insertReservation(reservation)
                    Navigation.findNavController(view).navigate(R.id.go_back_to_movies)
                    Log.i("_", reservation.toString())
                }
                else {
                    Toast.makeText(context, "Please check T&C", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Please fill with data!", Toast.LENGTH_SHORT).show()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    fun CharSequence.splitIgnoreEmpty(vararg delimiters: String): List<String> {
        return this.split(*delimiters).filter {
            it.isNotEmpty()
        }
    }
}