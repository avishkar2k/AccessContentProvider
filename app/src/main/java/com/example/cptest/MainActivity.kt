package com.example.cptest

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var CONTENT_URI = Uri.parse("content://com.example.cpapp.mycp/users/")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("Range", "SetTextI18n", "Recycle")
    fun onClickShowDetails(view: View?) {
        // inserting complete table details in this text field
        val resultView = findViewById<View>(R.id.res) as TextView

        // creating a cursor object of the
        // content URI
        val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)

        // iteration of the cursor
        // to print whole table
        try {
            if (cursor!!.moveToFirst()) {
                val strBuild = StringBuilder()
                while (!cursor.isAfterLast) {
                    strBuild.append("""
          
        ${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}
        """.trimIndent())
                    cursor.moveToNext()
                }
                resultView.text = strBuild
            } else {
                resultView.text = "No Records Found"
            }
        } catch (e: Exception) {
            Log.d(CONTENT_URI.toString(), "onClickShowDetails: "+e.localizedMessage)
        }
    }
}