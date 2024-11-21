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
import java.util.List;
import java.util.Optional;
import java.time.Period;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import br.com.catolica.transaction.dto.response.BookDTO;
import br.com.catolica.transaction.dto.response.UserDTO;



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

        // transactionDTO.setLoanDate(LocalDateTime.now());

        Transaction transaction = transactionMapper.dtoToEntity(transactionDTO);
        transactionRepository.save(transaction);
        return transactionMapper.entityToDTO(transaction);
    }


    public List<TransactionDTO> getAll() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::entityToDTO)
                .toList();
    }

    public TransactionDTO verificaMulta(Long id) throws Exception {
        var movimentacao = transactionRepository.findById(id).orElse(null);

        if (movimentacao == null) {
            throw new Exception("Movimentação não foi encontrada");
        }

        LocalDateTime prazo = movimentacao.getLoanDate().plusDays(3);
        LocalDateTime dataAtual = LocalDateTime.now();

        if (prazo.isBefore(dataAtual)) {
            Period diferenca = Period.between(prazo.toLocalDate(), dataAtual.toLocalDate());
            int totalDias = diferenca.getYears() * 365 + diferenca.getMonths() * 30 + diferenca.getDays();
            double valorMulta = 1.00 * totalDias;

            movimentacao.setFineValue(BigDecimal.valueOf(valorMulta));
            movimentacao.setReturnDate(dataAtual);

            transactionRepository.save(movimentacao);

            UserDTO user = userTransaction.getUserById(movimentacao.getUserId());
            BookDTO book = bookTransaction.getBookById(movimentacao.getBookId());

            System.out.println(
                    user.getName() + " está com o livro: " + book.getTitulo() +
                            " com " + totalDias + " dias de atraso"
            );
        } else {

            System.out.println("O livro ainda está no prazo de 3 dias!");

            movimentacao.setLoanDate(dataAtual); // Atualiza a data do empréstimo, se necessário
            transactionRepository.save(movimentacao);
        }

        return transactionMapper.entityToDTO(movimentacao);
    }





    public void movimentacoesComAtraso() {

        List<Transaction> movimentacoesSemDevolucao = transactionRepository
                .findByReturnDateIsNullOrReturnDateBefore(LocalDateTime.now());

        if (movimentacoesSemDevolucao.isEmpty()) {
            System.out.println("Não existem movimentações com atraso!");
        } else {
            System.out.println("Movimentações com atraso:");
        }


        for (Transaction m : movimentacoesSemDevolucao) {

            UserDTO user = userTransaction.getUserById(m.getUserId());
            BookDTO book = bookTransaction.getBookById(m.getBookId());

            System.out.println(
                    "ID Transação: " + m.getId() +
                            ", Usuário: " + (user != null ? user.getName() : "Desconhecido") + " (" + (user != null ? user.getCpf() : "N/A") + ")" +
                            ", Livro: " + (book != null ? book.getTitulo() : "Desconhecido") +
                            ", Data Empréstimo: " + m.getLoanDate() +
                            ", Data Devolução Esperada: " + m.getReturnDate()
            );
        }
    }
}
