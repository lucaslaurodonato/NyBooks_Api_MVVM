package com.lucasdonato.nybooks.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucasdonato.nybooks.R
import com.lucasdonato.nybooks.mechanism.Constants.EXTRA_CONTRIBUTOR
import com.lucasdonato.nybooks.mechanism.Constants.EXTRA_DESCRIPTION
import com.lucasdonato.nybooks.mechanism.Constants.EXTRA_TITLE
import kotlinx.android.synthetic.main.activity_book_details.*
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.view.*

class BookDetailsActivity :  AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context, title: String, description: String, contributor: String): Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_CONTRIBUTOR, contributor)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        toolbar.apply {
            title.text = getString(R.string.book_details_title)
            back.setOnClickListener { finish() }
        }

        bookDetailsTitle.text = intent.getStringExtra(EXTRA_TITLE)
        bookDetailsContributor.text = intent.getStringExtra(EXTRA_CONTRIBUTOR)
        bookDetailsDescription.text = intent.getStringExtra(EXTRA_DESCRIPTION)
    }
}
