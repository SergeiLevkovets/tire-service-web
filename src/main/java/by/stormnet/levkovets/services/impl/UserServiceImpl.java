package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.UserDao;
import by.stormnet.levkovets.domain.impl.User;
import by.stormnet.levkovets.dto.impl.UserDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements DtoService<UserDto> {

    @Override
    public UserDto getById(Integer id) {
        UserDao userDao = new UserDao();

        UserDto userDto = EntityDtoConverter.transformToUserDto(userDao.loadById(id));

        return userDto;
    }

    @Override
    public List<UserDto> getAll() {

        UserDao userDao = new UserDao();
        List<UserDto> list = new ArrayList<>();
        for (User user : userDao.loadAll()) {
            UserDto userDto = EntityDtoConverter.transformToUserDto(user);
            list.add(userDto);
        }
        return list;
    }

    @Override
    public void saveOrUpdate(UserDto userDto) {
        UserDao userDao = new UserDao();

        if (userDto.getId() == null) {
            userDao.save(EntityDtoConverter.transformToUserEntity(userDto));
        }else {
            userDao.update(EntityDtoConverter.transformToUserEntity(userDto));
        }

    }
}
