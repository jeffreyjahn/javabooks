package com.jeffreyahn.mvc.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jeffreyahn.mvc.models.Book;
import com.jeffreyahn.mvc.repositories.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
    // returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
	public Book updateBook(Long id, String title, String desc, String lang, Integer pages) {
        Book currBook = bookRepository.findById(id).get();
        currBook.setTitle(title);
        currBook.setDescription(desc);
        currBook.setLanguage(lang);
        currBook.setNumberOfPages(pages);
        currBook.setUpdatedAt(new Date());
        return bookRepository.save(currBook);
	}
	public void deleteBook(Long id) {
        bookRepository.deleteById(id);
	}
}
