package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.User;

import java.util.List;

public interface UserDAO {
    Integer save(User user);

    void update(User user);

    void delete(User user);

    User loadById(Integer id);

    List<User> loadAll();
}
