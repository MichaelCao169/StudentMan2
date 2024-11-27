package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class StudentAdapter(
    context: Context,
    private val students: List<StudentModel>
) : ArrayAdapter<StudentModel>(context, 0, students) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.student_list_item, parent, false)

        val student = students[position]

        val textStudentName: TextView = view.findViewById(R.id.text_student_name)
        val textStudentId: TextView = view.findViewById(R.id.text_student_id)

        textStudentName.text = student.studentName
        textStudentId.text = student.studentId

        return view
    }
}