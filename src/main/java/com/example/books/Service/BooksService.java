package com.example.books.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.books.dto.BookDTO;
import com.example.books.persistence.Book;
import com.example.books.persistence.BookRepository;

@Service
public class BooksService {
	@Autowired
	private BookRepository bookRepository;
	
	public void addBook(BookDTO bookDTO) {
		if (bookDTO != null) {
			Book book = this.bookDTO2Book(bookDTO);
			
			bookRepository.save(book);
		}
	}
	
	public List<BookDTO> getAllBooks() {
		List<Book> entities = bookRepository.findAll();
		List<BookDTO> result = null;
		
		if (!CollectionUtils.isEmpty(entities)) {
			result = entities.stream().map(this::book2BookDTO).collect(Collectors.toList());
		}
		
		return result;
	}
	
	private Book bookDTO2Book(BookDTO dto) {
		Book result = new Book();
		
		result.setAuthor(dto.getAuthor());
		result.setTitle(dto.getTitle());
		
		return result;
	}
	
	private BookDTO book2BookDTO(Book entity) {
		BookDTO result = new BookDTO();
		
		result.setTitle(entity.getTitle());
		result.setAuthor(StringUtils.isEmpty(entity.getAuthor()) ? "Unknown" : entity.getAuthor());
		
		return result;
	}
}
