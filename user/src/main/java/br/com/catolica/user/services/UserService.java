package br.com.catolica.user.services;

import br.com.catolica.user.domains.StandardUser;
import br.com.catolica.user.dto.UserDTO;
import br.com.catolica.user.mapper.UserMapper;
import br.com.catolica.user.repositories.UserRespository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRespository userRespository;
    private final UserMapper userMapper;

    public List<UserDTO> getAll() {
        return userRespository.findAll()
                .stream()
                .map(userMapper::entityToDTO)
                .toList();
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRespository.findById(id).map(userMapper::entityToDTO);
    }

    public UserDTO save(UserDTO userDTO) {
        Optional.ofNullable(userDTO.getId()).ifPresent(ex -> {
            throw new IllegalArgumentException("O id deve ser nulo");
        });

        StandardUser user = userRespository.save(userMapper.dtoToEntity(userDTO));

        return userMapper.entityToDTO(user);
    }

    public void deleteUser(Long id) {
        userRespository.deleteById(id);
    }
}
