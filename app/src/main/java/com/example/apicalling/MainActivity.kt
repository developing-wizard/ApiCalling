package com.example.apicalling

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalling.api.ApiClient
import com.example.apicalling.databinding.MainActivityBinding
import com.example.apicalling.datamodels.PostingData
import com.example.apicalling.userRecycler.UserAdapter
import com.example.apicalling.datamodels.UserData
import com.example.apicalling.userRecycler.FilterRecycler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

class MainActivity : ComponentActivity() {

    val binding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }
    private lateinit var userrecyclerView: RecyclerView
    private lateinit var userRVAdapter: UserAdapter
    private lateinit var userDataList: ArrayList<UserData>
    private lateinit var postrecyclerView: RecyclerView
    private lateinit var postRVAdapter: FilterRecycler
    private lateinit var postDataList: ArrayList<PostingData>
    private lateinit var postFilterDataList: ArrayList<PostingData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.Comment.setOnClickListener()
        {
            val call = ApiClient.apiService.getUser()
            call.enqueue(object : retrofit2.Callback<ArrayList<UserData>> {
                override fun onResponse(
                    call: Call<ArrayList<UserData>>,
                    response: Response<ArrayList<UserData>>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Succes", Toast.LENGTH_SHORT).show()
                        val post = response.body()
                        userDataList = post!!
                        userrecyclerView = binding.recycler
                        val layoutManager = GridLayoutManager(this@MainActivity, 2)
                        userrecyclerView.layoutManager = layoutManager
                        userRVAdapter = UserAdapter(this@MainActivity, userDataList)
                        userrecyclerView.adapter = userRVAdapter

                    } else {
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserData>>, t: Throwable) {
                }
            })
        }

        binding.getAllPost.setOnClickListener()
        {
            val call2 = ApiClient.apiService.getPost()

            call2.enqueue(object : Callback<ArrayList<PostingData>> {
                override fun onResponse(
                    call: Call<ArrayList<PostingData>>,
                    response: Response<ArrayList<PostingData>>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        postDataList = data!!
                        postrecyclerView = binding.recyclerfiltered
                        val layoutManager = GridLayoutManager(this@MainActivity, 2)
                        postrecyclerView.layoutManager = layoutManager
                        postRVAdapter = FilterRecycler(this@MainActivity, postDataList)
                        postrecyclerView.adapter = postRVAdapter

                    }
                }

                override fun onFailure(call: Call<ArrayList<PostingData>>, t: Throwable) {

                }

            })
        }


        binding.filterData.setOnClickListener() {


            val filtercall = ApiClient.apiService.getPost()
            filtercall.enqueue(object :Callback <ArrayList<PostingData>> {
                override fun onResponse(
                    call: Call<ArrayList<PostingData>>,
                    response: Response<ArrayList<PostingData>>
                ) {
                    val filterData = response.body()
                    postFilterDataList = filterData!!

                    postFilterDataList = postFilterDataList.filter {it.userId == 1} as ArrayList<PostingData>
                    postrecyclerView = binding.recyclerfiltered
                    val layoutManager = GridLayoutManager(this@MainActivity, 2)
                    postrecyclerView.layoutManager = layoutManager
                    postRVAdapter = FilterRecycler(this@MainActivity, postFilterDataList)
                    postrecyclerView.adapter = postRVAdapter

                }

                override fun onFailure(call: Call<ArrayList<PostingData>>, t: Throwable) {
                }

            }
            )
        }
    }
}

