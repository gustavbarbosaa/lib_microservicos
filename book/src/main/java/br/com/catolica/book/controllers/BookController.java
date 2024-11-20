package br.com.catolica.book.controllers;

import br.com.catolica.book.domains.Book;
import br.com.catolica.book.dto.BookDTO;
import br.com.catolica.book.mapper.BookMapper;
import br.com.catolica.book.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BookDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
