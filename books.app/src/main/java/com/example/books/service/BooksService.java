package com.example.books.service;

import java.util.List;

import com.example.books.dto.BookDTO;

public interface BooksService {
	void addBook(BookDTO bookDTO);
	List<BookDTO> getAllBooks();
}
