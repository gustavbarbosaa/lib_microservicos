package br.com.catolica.user.mapper;

import br.com.catolica.user.domains.StandardUser;
import br.com.catolica.user.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    StandardUser dtoToEntity(UserDTO userDTO);

    UserDTO entityToDTO(StandardUser user);
}
