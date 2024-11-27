package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private val students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val listViewStudents: ListView = findViewById(R.id.list_view_students)
        studentAdapter = StudentAdapter(this, students)
        listViewStudents.adapter = studentAdapter

        registerForContextMenu(listViewStudents)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_student -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivityForResult(intent, ADD_STUDENT_REQUEST_CODE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.student_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        val student = students[position]

        return when (item.itemId) {
            R.id.menu_edit_student -> {
                val intent = Intent(this, EditStudentActivity::class.java).apply {
                    putExtra("student_name", student.studentName)
                    putExtra("student_id", student.studentId)
                    putExtra("position", position)
                }
                startActivityForResult(intent, EDIT_STUDENT_REQUEST_CODE)
                true
            }
            R.id.menu_remove_student -> {
                students.removeAt(position)
                studentAdapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                ADD_STUDENT_REQUEST_CODE -> {
                    val newStudentName = data?.getStringExtra("student_name")
                    val newStudentId = data?.getStringExtra("student_id")
                    if (newStudentName != null && newStudentId != null) {
                        students.add(StudentModel(newStudentName, newStudentId))
                        studentAdapter.notifyDataSetChanged()
                    }
                }
                EDIT_STUDENT_REQUEST_CODE -> {
                    val position = data?.getIntExtra("position", -1)
                    val updatedName = data?.getStringExtra("student_name")
                    val updatedId = data?.getStringExtra("student_id")
                    if (position != null && position != -1 && updatedName != null && updatedId != null) {
                        students[position] = StudentModel(updatedName, updatedId)
                        studentAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    companion object {
        private const val ADD_STUDENT_REQUEST_CODE = 1
        private const val EDIT_STUDENT_REQUEST_CODE = 2
    }
}