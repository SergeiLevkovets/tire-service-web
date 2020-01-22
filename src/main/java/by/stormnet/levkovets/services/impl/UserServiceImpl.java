package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.UserDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.User;
import by.stormnet.levkovets.dto.impl.UserDTO;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements by.stormnet.levkovets.services.UserService {

    @Override
    public UserDTO getById(Integer id) {
        UserDAO userDao = DAOFactory.getFactory().getUserDAO();

        UserDTO userDto = EntityDtoConverter.transformToUserDto(userDao.loadById(id));

        return userDto;
    }

    @Override
    public List<UserDTO> getAll() {

        UserDAO userDao = DAOFactory.getFactory().getUserDAO();
        List<UserDTO> list = new ArrayList<>();
        for (User user : userDao.loadAll()) {
            UserDTO userDto = EntityDtoConverter.transformToUserDto(user);
            list.add(userDto);
        }
        return list;
    }

    @Override
    public void delete(UserDTO obj) {
        UserDAO dao = DAOFactory.getFactory().getUserDAO();
        dao.delete(EntityDtoConverter.transformToUserEntity(obj));
    }

    @Override
    public Integer saveOrUpdate(UserDTO userDto) {
        UserDAO userDao = DAOFactory.getFactory().getUserDAO();
        Integer id = userDto.getId();
        if (id == null) {
            id = userDao.save(EntityDtoConverter.transformToUserEntity(userDto));
        } else {
            userDao.update(EntityDtoConverter.transformToUserEntity(userDto));
        }
        return id;
    }
}
