package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.UserDto;

import java.util.List;

public interface UserService extends DtoService<UserDto> {
    @Override
    UserDto getById(Integer id);

    @Override
    List<UserDto> getAll();

    @Override
    void delete(UserDto obj);

    @Override
    void saveOrUpdate(UserDto userDto);
}
