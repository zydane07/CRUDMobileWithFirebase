package com.example.crudapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var name : EditText
    private lateinit var nickName : EditText
    private lateinit var btnSubmit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.name)
        nickName = findViewById(R.id.nick_name)
        btnSubmit = findViewById(R.id.btn_submit)

        btnSubmit.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        saveData()
    }

    private fun saveData() {
        val nameText = name.text.toString().trim()
        val nickText = nickName.text.toString().trim()

        if (nameText.isEmpty()){
            name.error = "isi nama!"
            return
        }

        if (nickText.isEmpty()){
            nickName.error = "Isi Nick Name!"
            return
        }

//        if (nameText != null && nickText != null) {
//            Toast.makeText(applicationContext,"Data Berhasil Ditambah", Toast.LENGTH_SHORT).show()
//        }


        val ref = FirebaseDatabase.getInstance().getReference("users")

        val userId = ref.push().key

        val saveUser = Users(userId, nameText, nickText)

        if (userId != null) {
            ref.child(userId).setValue(saveUser).addOnCompleteListener{
                Toast.makeText(applicationContext,"Data Berhasil Ditambah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}