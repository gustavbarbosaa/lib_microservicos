package br.com.catolica.book.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String escritor;
    @Column(nullable = false)
    private LocalDateTime dataPublicacao;
    @Column(nullable = false)
    private String genero;
    @Column(nullable = false)
    private String editora;
    @Column(nullable = false)
    private String edicao;
    @Column(nullable = false)
    private String categoria;
    @Column(nullable = false)
    private String isbn;

}
