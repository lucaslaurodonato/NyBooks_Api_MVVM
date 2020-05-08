package com.lucasdonato.nybooks.presentation.books.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucasdonato.nybooks.presentation.details.BookDetailsActivity
import com.lucasdonato.nybooks.R
import com.lucasdonato.nybooks.data.repository.BooksApiDataSource
import com.lucasdonato.nybooks.presentation.books.adapter.BooksAdapter
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.view.*

class BooksActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context): Intent = Intent(context, BooksActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

       toolbarMain.apply {
           title.text = getString(R.string.books_title)
           back.visibility = GONE
       }

        val viewModel: BooksViewModel = BooksViewModel.ViewModelFactory(BooksApiDataSource())
            .create(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(recyclerBooks) {
                    layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                    adapter = BooksAdapter(books) { book ->
                        val intent = BookDetailsActivity.getStartIntent(this@BooksActivity, book.title, book.description, book.contributor)
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperBooks.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessageResId ->
                    textViewError.text = getString(errorMessageResId)
                }
            }
        })
        viewModel.getBooks()
    }
}






