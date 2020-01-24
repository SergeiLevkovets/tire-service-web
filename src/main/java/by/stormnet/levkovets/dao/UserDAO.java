package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.User;

import java.util.List;

public interface UserDAO {
    Integer save(User user);

    void update(User user);

    void deleteById(Integer id);

    User loadById(Integer id);

    List<User> loadAll();
}
