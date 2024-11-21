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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/save")
    public ResponseEntity<TransactionDTO> save(@Valid @RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transactionDTO));
    }


    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransaction() {return ResponseEntity.ok().body(transactionService.getAll());}

    @GetMapping("/movimentacoesatraso")
    public ResponseEntity<Void> getMovimentacoesComAtraso() {
        transactionService.movimentacoesComAtraso();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verificarmulta/{id}")
    public ResponseEntity<TransactionDTO> verifyFine(@PathVariable Long id) {
        try {
            TransactionDTO transactionDTO = transactionService.verificaMulta(id);

            return ResponseEntity.ok(transactionDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
