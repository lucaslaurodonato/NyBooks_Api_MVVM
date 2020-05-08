package com.lucasdonato.nybooks.data.repository

import com.lucasdonato.nybooks.data.BooksResult

interface BooksRepository {

    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}