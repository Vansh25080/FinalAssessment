package com.example.myassssmentapplication.ui.details

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myassssmentapplication.R
import com.example.myassssmentapplication.data.model.Entity

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

        val entity = intent.getSerializableExtra(EXTRA_ENTITY) as? Entity

        entity?.let {
            tvProp1.text = "Asset Type: ${it.assetType ?: "N/A"}"
            tvProp2.text = "Ticker: ${it.ticker ?: "N/A"}"
            tvDescription.text = "Description: ${it.description ?: "N/A"}\n\n" +
                    "Current Price: ${it.currentPrice ?: "N/A"}\n\n" +
                    "Dividend Yield: ${it.dividendYield ?: "N/A"}"
        }
    }
}
