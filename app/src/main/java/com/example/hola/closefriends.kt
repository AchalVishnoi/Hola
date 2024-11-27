package com.example.hola

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class closefriends : AppCompatActivity() {

    private lateinit var closeFriendsRecyclerView: RecyclerView
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_closefriends)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.close_friends)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        closeFriendsRecyclerView = findViewById(R.id.closeFriendsRecyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        val doneButton = findViewById<Button>(R.id.doneButton)

        doneButton.setOnClickListener {
            Toast.makeText(this, "Done clicked!", Toast.LENGTH_SHORT).show()
        }

        // Sample data
        val closeFriendsList = listOf(
            closefriendsData("Alice", R.drawable.image1, false),
            closefriendsData("Bob", R.drawable.image1, false),
            closefriendsData("Charlie", R.drawable.image2, false),
            closefriendsData("Alice", R.drawable.image2, false),
            closefriendsData("Bob", R.drawable.image2, false),
            closefriendsData("Charlie", R.drawable.image1, false),
            closefriendsData("Alice", R.drawable.image2, false),
            closefriendsData("Bob", R.drawable.image1, false),
            closefriendsData("Charlie", R.drawable.image2, false),
            closefriendsData("Alice", R.drawable.image2, false),
            closefriendsData("Bob", R.drawable.image2, false),
            closefriendsData("Charlie", R.drawable.image1, false),
            closefriendsData("Alice", R.drawable.image2, false),
            closefriendsData("Bob", R.drawable.image1, false),
            closefriendsData("Charlie", R.drawable.image2, false),
            closefriendsData("Alice", R.drawable.image1, false),
        closefriendsData("Bob", R.drawable.image2, false),
        closefriendsData("Charlie", R.drawable.image1, false)
        )

        // Set up RecyclerView
        closeFriendsRecyclerView.layoutManager = LinearLayoutManager(this)
        closeFriendsRecyclerView.adapter = closefriendsAdapter(closeFriendsList)

        // Search functionality
//        searchEditText.addTextChangedListener { text ->
//            val filteredList = closeFriendsList.filter { it.name.contains(text.toString(), ignoreCase = true) }
//            closeFriendsRecyclerView.adapter = closefriendsAdapter(filteredList)
//        }

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredList = closeFriendsList.filter { it.name.contains(s.toString(), ignoreCase = true) }
                closeFriendsRecyclerView.adapter = closefriendsAdapter(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed here
            }

        })
    }
}