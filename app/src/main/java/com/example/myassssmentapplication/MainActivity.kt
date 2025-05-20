package com.example.myassssmentapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvResult = findViewById(R.id.tvResult)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                tvResult.text = "Please fill all fields"
                return@setOnClickListener
            }

            val request = LoginRequest(username, password)

            Log.d("LoginDebug", "Button clicked with: $username, $password")
            Log.d("LoginDebug", "Sending login request...")

            RetrofitClient.instance.login(request).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    Log.d("LoginDebug", "Response received: ${response.code()}")

                    if (response.isSuccessful) {
                        val keypass = response.body()?.keypass

                        if (!keypass.isNullOrEmpty()) {
                            tvResult.text = "Login success! keypass: $keypass"
                            Log.d("LoginDebug", "Login success! keypass: $keypass")

                            // ✅ Launch DashboardActivity with keypass
                            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                            intent.putExtra("keypass", keypass)
                            startActivity(intent)
                        } else {
                            tvResult.text = "Login failed: keypass missing"
                            Log.e("LoginDebug", "Login failed: keypass was null or empty")
                        }
                    } else {
                        tvResult.text = "Login failed. Check credentials."
                        Log.e("LoginDebug", "Login failed: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    tvResult.text = "Network error: ${t.message}"
                    Log.e("LoginDebug", "Network error: ${t.message}")
                }
            })
        }
    }
}
