package br.com.catolica.user.repositories;

import br.com.catolica.user.domains.StandardUser;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRespository extends JpaRepository<StandardUser, Long> {

    @Override
    <U extends StandardUser> List<U> findAll(Example<U> user);

    @Override
    void delete(StandardUser user);
}
