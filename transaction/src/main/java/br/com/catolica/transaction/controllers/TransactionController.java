package br.com.catolica.transaction.controllers;

import br.com.catolica.transaction.dto.TransactionDTO;
import br.com.catolica.transaction.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/save")
    public ResponseEntity<TransactionDTO> save(@Valid @RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transactionDTO));
    }
}
