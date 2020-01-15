package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.UserDao;
import by.stormnet.levkovets.domain.impl.User;
import by.stormnet.levkovets.dto.UserDto;
import by.stormnet.levkovets.services.UserService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public UserDto getUserById(Integer id) {
        UserDao userDao = new UserDao();

        UserDto userDto = EntityDtoConverter.transformToUserDto(userDao.loadById(id));

        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        UserDao userDao = new UserDao();
        List<UserDto> list = new ArrayList<>();
        for (User user : userDao.loadAll()) {
            UserDto userDto = EntityDtoConverter.transformToUserDto(user);
            list.add(userDto);
        }
        return list;
    }

    @Override
    public void saveOrUpdateUser(UserDto userDto) {
        UserDao userDao = new UserDao();

        if (userDto.getId() == null) {
            userDao.save(EntityDtoConverter.transformToUser(userDto));
        }else {
            userDao.update(EntityDtoConverter.transformToUser(userDto));
        }

    }
}
