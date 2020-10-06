package com.example.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.books.dto.BookDTO;
import com.example.books.service.BooksServiceImpl;

@RestController
@RequestMapping("/book-management")
public class BooksController {
	@Autowired
	private BooksServiceImpl booksService;
	
	
	@PostMapping("/books")
	public ResponseEntity<Void> saveBook(@RequestBody BookDTO book) {
		booksService.addBook(book);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<BookDTO>> getBooks() {
		List<BookDTO> result = booksService.getAllBooks();

		return ResponseEntity.ok(result);
	}
}
