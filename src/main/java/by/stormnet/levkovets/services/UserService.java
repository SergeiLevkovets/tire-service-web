package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(Integer id);
    List<UserDto> getAllUsers();
    void saveOrUpdateUser(UserDto userDto);
}
