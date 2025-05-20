package com.example.myassssmentapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val keypass = intent.getStringExtra("keypass")

        if (keypass.isNullOrEmpty()) {
            Toast.makeText(this, "Missing keypass", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        Log.d("DashboardDebug", "Keypass received: $keypass")
        fetchDashboardData(keypass)
    }

    private fun fetchDashboardData(keypass: String) {
        RetrofitClient.instance.getDashboardData(keypass).enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                Log.d("DashboardDebug", "Dashboard response: ${response.code()}")
                if (response.isSuccessful) {
                    val dashboardData = response.body()
                    if (dashboardData != null) {
                        tvEntityTotal.text = "Total Entities: ${dashboardData.entityTotal}"
                        setupRecyclerView(dashboardData.entities)
                    } else {
                        Toast.makeText(this@DashboardActivity, "Empty response", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("DashboardDebug", "Error: ${response.errorBody()?.string()}")
                    Toast.makeText(this@DashboardActivity, "Failed to fetch data (code ${response.code()})", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                Log.e("DashboardDebug", "Network error: ${t.message}")
                Toast.makeText(this@DashboardActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView(entities: List<Entity>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        entityAdapter = EntityAdapter(entities) { selectedEntity ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.EXTRA_ENTITY, selectedEntity)
            startActivity(intent)
        }
        recyclerView.adapter = entityAdapter
    }
}
