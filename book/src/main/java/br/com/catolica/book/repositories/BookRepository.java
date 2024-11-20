package br.com.catolica.book.repositories;

import br.com.catolica.book.domains.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Book, Long> {
}
