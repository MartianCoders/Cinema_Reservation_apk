package com.example.rezervarecinemaiss.AccoutActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.rezervarecinemaiss.Data.User
import android.widget.RadioButton
import com.example.rezervarecinemaiss.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment: Fragment() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        val backButton: Button = view.findViewById(R.id.button_back_to_login)

        backButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.back_to_login)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usernameEditText: EditText = view.findViewById(R.id.editText_username)
        val passwordEditText: EditText = view.findViewById(R.id.editText_password)
        val repeatPasswordEditText: EditText = view.findViewById(R.id.editText_repeat_password)
        val radioButtonGroup: RadioGroup = view.findViewById(R.id.radio_group_signup)
        val signUpButton: Button = view.findViewById(R.id.button_signup)

        signUpButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val repeatPassword = repeatPasswordEditText.text.toString()

            val selectedId: Int = radioButtonGroup.checkedRadioButtonId
            val radioButton = view.findViewById(selectedId) as RadioButton


            if(password == repeatPassword && username.isNotEmpty()) {
                val isAdmin = radioButton.text.toString() == "Admin"
                val user = User(username = username, password = password, admin = isAdmin)
                viewModel.insertRecord(user)
                Navigation.findNavController(view).navigate(R.id.back_to_login)
                Toast.makeText(context, "Account created!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Passwords are different!", Toast.LENGTH_SHORT).show()
            }
        }


        super.onViewCreated(view, savedInstanceState)
    }
}