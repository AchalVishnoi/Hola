package com.example.hola

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Home : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getApiData()
        retrofitData.enqueue(object : Callback<ApiData?> {
            override fun onResponse(call: Call<ApiData?>, response: Response<ApiData?>) {
                // Check if the response is successful and the body is not null
                response.body()?.let { responseBody ->
                    val productList = responseBody.products

                    myAdapter = MyAdapter(requireContext(), productList)
                    recyclerView.adapter = myAdapter
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                } ?: Log.d("Ho_Me", "Response body is null")
            }
            override fun onFailure(call: Call<ApiData?>, t: Throwable) {
                Log.d("Ho_Me", "onFailure: ${t.message}")
            }
        })
        return view // Ensure only one view is returned
    }
}