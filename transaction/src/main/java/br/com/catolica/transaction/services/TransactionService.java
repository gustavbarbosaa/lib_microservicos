package br.com.catolica.transaction.services;

import br.com.catolica.transaction.client.BookTransaction;
import br.com.catolica.transaction.client.UserTransaction;
import br.com.catolica.transaction.domain.Transaction;
import br.com.catolica.transaction.dto.TransactionDTO;
import br.com.catolica.transaction.dto.response.BookDTO;
import br.com.catolica.transaction.dto.response.UserDTO;
import br.com.catolica.transaction.mapper.TransactionMapper;
import br.com.catolica.transaction.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final BookTransaction bookTransaction;
    private final UserTransaction userTransaction;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionDTO save(@RequestBody TransactionDTO transactionDTO) {

        if (transactionDTO.getBookId() == null || transactionDTO.getUserId() == null) {
            throw new IllegalArgumentException("Os ids do livro e do usuário são obrigatórios!");
        }

        BookDTO book = bookTransaction.getBookById(transactionDTO.getBookId());
        UserDTO user = userTransaction.getUserById(transactionDTO.getUserId());

        if (book == null) {
            throw new IllegalStateException("Livro não encontrado com o ID: " + transactionDTO.getBookId());
        }

        if (user == null) {
            throw new IllegalStateException("Usuário não encontrado com o ID: " + transactionDTO.getUserId());
        }

        transactionDTO.setLoanDate(LocalDateTime.now());

        Transaction transaction = transactionMapper.dtoToEntity(transactionDTO);
        transactionRepository.save(transaction);
        return transactionMapper.entityToDTO(transaction);
    }
}
