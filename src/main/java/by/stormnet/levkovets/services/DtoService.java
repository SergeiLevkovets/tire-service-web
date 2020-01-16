package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.Dto;
import by.stormnet.levkovets.dto.impl.UserDto;

import java.util.List;

public interface DtoService<T extends Dto> {
    T getById(Integer id);
    List<T> getAll();
    void saveOrUpdate(T obj);
    void delete(T obj);
}
