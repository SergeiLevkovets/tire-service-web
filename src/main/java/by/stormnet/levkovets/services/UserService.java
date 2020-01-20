package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getById(Integer id);

    List<UserDTO> getAll();

    void delete(UserDTO obj);

    void saveOrUpdate(UserDTO userDto);
}
