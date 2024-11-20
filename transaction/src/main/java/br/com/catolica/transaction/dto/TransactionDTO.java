package br.com.catolica.transaction.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDTO {
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private LocalDateTime loanDate;

    @Column(nullable = false)
    private LocalDateTime returnDate;

    @Column(nullable = false)
    private BigDecimal fineValue;
}
