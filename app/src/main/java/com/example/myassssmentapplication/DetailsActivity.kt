package com.example.myassssmentapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ENTITY = "extra_entity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val tvProp1 = findViewById<TextView>(R.id.tvProp1)
        val tvProp2 = findViewById<TextView>(R.id.tvProp2)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)

        // Receive entity from intent
        val entity = intent.getSerializableExtra(EXTRA_ENTITY) as? Entity

        entity?.let {
            tvProp1.text = it.property1
            tvProp2.text = it.property2
            tvDescription.text = it.description
        }
    }
}
