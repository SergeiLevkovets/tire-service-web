package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.TypeDto;

import java.util.List;

public interface TypeService extends DtoService<TypeDto> {
    @Override
    TypeDto getById(Integer id);

    TypeDto getTypeByName(String name);

    @Override
    void delete(TypeDto obj);

    @Override
    List<TypeDto> getAll();

    @Override
    void saveOrUpdate(TypeDto typeDto);
}
