package br.com.catolica.transaction.client;

import br.com.catolica.transaction.dto.response.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "${feign.books.name}", url = "${feign.books.url}")
public interface BookTransaction {
    @GetMapping("/{id}")
    BookDTO getBookById(@PathVariable Long id);
}
