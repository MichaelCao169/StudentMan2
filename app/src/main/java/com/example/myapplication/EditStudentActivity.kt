package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val editTextName: EditText = findViewById(R.id.edit_text_student_name)
        val editTextId: EditText = findViewById(R.id.edit_text_student_id)
        val buttonSave: Button = findViewById(R.id.button_save)

        // Add null checks and provide default values
        val currentName = intent.getStringExtra("student_name") ?: ""
        val currentId = intent.getStringExtra("student_id") ?: ""
        position = intent.getIntExtra("position", -1)

        // Add logging or debugging toast if position is invalid
        if (position == -1) {
            Toast.makeText(this, "Error: Invalid student position", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        editTextName.setText(currentName)
        editTextId.setText(currentId)

        buttonSave.setOnClickListener {
            val studentName = editTextName.text.toString().trim()
            val studentId = editTextId.text.toString().trim()

            if (studentName.isNotEmpty() && studentId.isNotEmpty()) {
                val resultIntent = Intent().apply {
                    putExtra("student_name", studentName)
                    putExtra("student_id", studentId)
                    putExtra("position", position)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}