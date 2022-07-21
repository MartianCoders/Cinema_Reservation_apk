package com.example.rezervarecinemaiss.AccoutActivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.rezervarecinemaiss.Data.User
import com.example.rezervarecinemaiss.R
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import com.example.rezervarecinemaiss.RezervationActivity.ReservationActivity


@AndroidEntryPoint
class LoginFragment: Fragment() {

    private val viewModel by viewModels<MainActivityViewModel>()
    lateinit var userList: List<User>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val signUpTextView: TextView = view.findViewById(R.id.signup_textview)

        val loginButton: Button = view.findViewById(R.id.login_button)

        val usernameEditText: EditText = view.findViewById(R.id.editText_username)
        val passwordEditText: EditText = view.findViewById(R.id.editText_password)

        viewModel.getRecordsObserver().observe(this, object: Observer<List<User>> {
            override fun onChanged(t: List<User>?) {
                userList = t!!
            }
        })


        signUpTextView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.go_to_signup)
            Log.i("_", userList.toString())
        }

        loginButton.setOnClickListener {
            var connected = false
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            for(user: User in userList) {
                if (user.username == username && user.password == password) {
                    val intent = Intent(activity, ReservationActivity::class.java)
                    intent.putExtra("user", user.id.toString())
                    connected = true
                    startActivity(intent)
                }
            }
            if(!connected)
                Toast.makeText(context, "Wrong username/password!", Toast.LENGTH_SHORT).show()

        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

}