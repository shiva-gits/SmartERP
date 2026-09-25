package com.smartERP.Backend.Services;

import com.smartERP.Backend.Entities.Book;
import com.smartERP.Backend.DTOs.BookDTO;
import com.smartERP.Backend.Repository.BookRepository;
import com.smartERP.Backend.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO.BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public BookDTO.BookResponse getBookId(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
        return mapToResponse(book);
    }

    public BookDTO.BookResponse createBook(BookDTO.BookRequest request) {
        if (bookRepository.existsByIsbn(request.isbn())) {
            throw new IllegalArgumentException("Book with ISBN: " + request.isbn() + "already Exists!");
        }
        Book book = Book.builder()
                .isbn(request.isbn())
                .title(request.title())
                .author(request.author())
                .price(request.price())
                .build();

        return mapToResponse(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);
    }

    private BookDTO.BookResponse mapToResponse(Book book) {
        return new BookDTO.BookResponse(
                book.getId(),
                book.getIsbn(),
                book.getAuthor(),
                book.getTitle(),
                book.getPrice());
    }
}
