package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.TypeDTO;

import java.util.List;

public interface TypeService {
    TypeDTO getById(Integer id);

    TypeDTO getByName(String name);

    void delete(TypeDTO obj);

    List<TypeDTO> getAll();

    void saveOrUpdate(TypeDTO typeDto);
}
