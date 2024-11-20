package br.com.catolica.user.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class BookDTO {
    private Long id;
    private String titulo;
    private String escritor;
    private LocalDateTime dataPublicacao;
    private String genero;
    private String editora;
    private String edicao;
    private String categoria;
    private String isbn;
}
