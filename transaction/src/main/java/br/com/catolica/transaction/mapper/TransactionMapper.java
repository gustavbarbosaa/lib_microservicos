package br.com.catolica.transaction.mapper;

import br.com.catolica.transaction.domain.Transaction;
import br.com.catolica.transaction.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TransactionMapper {
    @Mapping(target = "id", ignore = true)
    Transaction dtoToEntity(TransactionDTO transactionDTO);

    TransactionDTO entityToDTO(Transaction transaction);
}
