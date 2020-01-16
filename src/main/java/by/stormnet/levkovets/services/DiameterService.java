package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.DiameterDto;

import java.util.List;

public interface DiameterService extends DtoService<DiameterDto> {
    @Override
    DiameterDto getById(Integer id);

    DiameterDto getByName(String name);

    @Override
    void delete(DiameterDto obj);

    @Override
    List<DiameterDto> getAll();

    @Override
    void saveOrUpdate(DiameterDto diameterDto);
}
