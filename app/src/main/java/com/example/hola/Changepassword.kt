package com.example.hola

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import com.android.volley.Request


class Changepassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        val oldPassword = findViewById<EditText>(R.id.old_password)
        val newPassword = findViewById<EditText>(R.id.new_password)
        val confirmPassword = findViewById<EditText>(R.id.confirm_new_password)
        val submitButton = findViewById<Button>(R.id.confirm_password_Button)


         fun updatePassword(oldPwd: String, newPwd: String) {
            val url = "http://hola-project.onrender.com/api/auth/change-password/"
            val requestBody = JSONObject().apply {
                put("oldPassword", oldPwd)
                put("newPassword", newPwd)
            }

            val request = JsonObjectRequest(Request.Method.POST, url, requestBody,
                { response ->
                    // Handle successful response
                    Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                },
                { error ->
                    // Handle error response
                    Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            )

            // Add request to queue
            Volley.newRequestQueue(this).add(request)
        }

        submitButton.setOnClickListener {
            val oldPwd = oldPassword.text.toString().trim()
            val newPwd = newPassword.text.toString().trim()
            val confirmPwd = confirmPassword.text.toString().trim()

            if (oldPwd.isEmpty() || newPwd.isEmpty() || confirmPwd.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPwd != confirmPwd) {
                Toast.makeText(this, "New password and confirm password do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Make API request
            updatePassword(oldPwd, newPwd)
        }



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_changepassword)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ChangePassword)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

