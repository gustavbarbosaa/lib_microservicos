package br.com.catolica.transaction.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookDTO {
    private Long id;
    @NotNull(message = "O campo título é obrigatório!")
    private String titulo;
    @NotNull(message = "O campo escritor é obrigatório!")
    private String escritor;
    @NotNull(message = "O campo data de publicação é obrigatório!")
    private LocalDateTime dataPublicacao;
    @NotNull(message = "O campo gênero é obrigatório!")
    private String genero;
    @NotNull(message = "O campo editora é obrigatório!")
    private String editora;
    @NotNull(message = "O campo edição é obrigatório!")
    private String edicao;
    @NotNull(message = "O campo categoria é obrigatório!")
    private String categoria;
    @NotNull(message = "O campo ISBN é obrigatório!")
    private String isbn;
}

