package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.YandexAPI
import com.example.myapplication.model.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    // https://predictor.yandex.net?
    // key=pdct.1.1.20220412T145449Z.ed53b660ddacdc8e.353ee4c0c5adc174b6be636450d97faa6e34a072

    val BASE_URL = "https://predictor.yandex.net"
    val KEY = "pdct.1.1.20220412T145449Z.ed53b660ddacdc8e.353ee4c0c5adc174b6be636450d97faa6e34a072"
    val LANG = "en"
    val LIMIT = 5

    lateinit var editText: EditText
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                doRequest()
            }
        })
    }

    fun doRequest() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: YandexAPI = retrofit.create(YandexAPI::class.java)
        api.getComplete(
            KEY,
            editText.text.toString(),
            LANG,
            LIMIT
        ).enqueue(object: Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.code() == 200) {
                    val result: List<String>? = response.body()!!.text
                    textView.text = result!!.joinToString("\n")
                }
            }
            override fun onFailure(call: Call<Response>, t: Throwable) {

            }
        })

    }
}