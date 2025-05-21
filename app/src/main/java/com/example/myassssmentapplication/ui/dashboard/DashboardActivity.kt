package com.example.myassssmentapplication.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myassssmentapplication.R
import com.example.myassssmentapplication.data.api.RetrofitClient
import com.example.myassssmentapplication.data.model.DashboardResponse
import com.example.myassssmentapplication.data.model.EntityAdapter
import com.example.myassssmentapplication.ui.details.DetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvEntityTotal: TextView
    private lateinit var entityAdapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        recyclerView = findViewById(R.id.recyclerView)
        tvEntityTotal = findViewById(R.id.tvEntityTotal)

        recyclerView.layoutManager = LinearLayoutManager(this)
        entityAdapter = EntityAdapter(emptyList()) { selectedEntity ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.EXTRA_ENTITY, selectedEntity)
            startActivity(intent)
        }
        recyclerView.adapter = entityAdapter

        val keypass = intent.getStringExtra("keypass")
        Log.d("DashboardDebug", "Keypass received: $keypass")
        if (keypass.isNullOrEmpty()) {
            Toast.makeText(this, "Missing keypass", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        fetchDashboardData(keypass)
    }

    private fun fetchDashboardData(keypass: String) {
        Log.d("DashboardDebug", "Fetching dashboard data with keypass: $keypass")
        RetrofitClient.instance.getDashboardData(keypass).enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                Log.d("DashboardDebug", "Dashboard response: ${response.code()}")
                if (response.isSuccessful) {
                    val dashboardData = response.body()
                    if (dashboardData != null && dashboardData.entities.isNotEmpty()) {
                        Log.d("DashboardDebug", "Dashboard data received with ${dashboardData.entities.size} items")
                        tvEntityTotal.text = "Total Entities: ${dashboardData.entityTotal}"
                        updateRecyclerView(dashboardData.entities)
                    } else {
                        Toast.makeText(this@DashboardActivity, "No entities available", Toast.LENGTH_SHORT).show()
                        Log.w("DashboardDebug", "Empty or null data received")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("DashboardDebug", "Error response: $errorBody")
                    Toast.makeText(this@DashboardActivity, "Failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                Log.e("DashboardDebug", "Network error: ${t.message}")
                Toast.makeText(this@DashboardActivity, "Network error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateRecyclerView(entities: List<*>) {
        @Suppress("UNCHECKED_CAST")
        entityAdapter.updateData(entities as List<com.example.myassssmentapplication.data.model.Entity>)
    }
}
