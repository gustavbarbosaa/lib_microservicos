package br.com.catolica.transaction.repositories;

import br.com.catolica.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
