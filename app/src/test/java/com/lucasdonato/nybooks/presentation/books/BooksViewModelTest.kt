package com.lucasdonato.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lucasdonato.nybooks.R
import com.lucasdonato.nybooks.data.BooksResult
import com.lucasdonato.nybooks.data.model.Book
import com.lucasdonato.nybooks.data.repository.BooksRepository
import com.lucasdonato.nybooks.data.response.BookResultsResponse
import com.lucasdonato.nybooks.presentation.books.view.BooksViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: BooksViewModel

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    @Test
    fun `when my view model getBooks get success then set bookLiveData`() {
        //Arrange
        var books = mutableListOf<Book>().apply {
            add(Book("title 1", "author 1", "description 1", "contributor1", "publisher1"))
        }

        val resultSuccess = MockRepository(BooksResult.Success(books))

        viewModel = BooksViewModel(resultSuccess)
        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getBooks()

        //Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when my view model getBooks get server error then set bookLiveData`() {
        //Arrange
        val resultServerError = MockRepository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServerError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getBooks()

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_500_generic))
    }
}

class MockRepository(private val result: BooksResult) : BooksRepository {

    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        booksResultCallback(result)
    }

}