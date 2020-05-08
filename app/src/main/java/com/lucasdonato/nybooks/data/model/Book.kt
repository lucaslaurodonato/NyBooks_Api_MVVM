package com.lucasdonato.nybooks.data.model

data class Book(
    val title: String,
    val author: String,
    val description: String,
    val contributor: String,
    val publisher: String,
    var isSelected: Boolean = false
)