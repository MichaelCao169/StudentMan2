package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val editTextName: EditText = findViewById(R.id.edit_text_student_name)
        val editTextId: EditText = findViewById(R.id.edit_text_student_id)
        val buttonSave: Button = findViewById(R.id.button_save)

        buttonSave.setOnClickListener {
            val studentName = editTextName.text.toString().trim()
            val studentId = editTextId.text.toString().trim()

            if (studentName.isNotEmpty() && studentId.isNotEmpty()) {
                val resultIntent = Intent().apply {
                    putExtra("student_name", studentName)
                    putExtra("student_id", studentId)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}