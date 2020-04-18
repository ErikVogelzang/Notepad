package com.example.notepad.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.notepad.R
import com.example.notepad.model.EditViewModel
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {

    private lateinit var editViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.edit_title)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
            editViewModel.note.value?.apply {
                title = etTitle.text.toString()
                lastUpdated = Date()
                text = etNote.text.toString()
            }

            editViewModel.updateNote()
        }
    }

    private fun initViewModel() {
        editViewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        editViewModel.note.value = intent.extras?.getParcelable(EXTRA_NOTE)!!
        editViewModel.note.observe(this, androidx.lifecycle.Observer { note ->
            if (note != null) {
                etTitle.setText(note.title)
                etNote.setText(note.text)
            }
        })
        editViewModel.error.observe(this, androidx.lifecycle.Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
        editViewModel.succes.observe(this, androidx.lifecycle.Observer {succes ->
            if (succes)
                finish()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_NOTE = "EXTRA_NOTE"
    }
}
