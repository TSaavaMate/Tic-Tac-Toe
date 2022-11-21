package com.example.tiktaktoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var name1:EditText
    private lateinit var name2:EditText
    private lateinit var Start:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name1=findViewById(R.id.p1name)
        name2=findViewById(R.id.p2name)
        Start=findViewById(R.id.button)
        Log.d("Tag","onCreate")
        Start.setOnClickListener {
            if (TextUtils.isEmpty(name1.text) || TextUtils.isEmpty(name2.text)){
                name1.error="name is required"
            }else{
                var n1=name1.text.toString()
                var n2=name2.text.toString()
                val intent = Intent(this, Secactivity::class.java)
                intent.putExtra("NAME1", n1)
                intent.putExtra("NAME2", n2)
                startActivity(intent)

            }




        }
    }
}