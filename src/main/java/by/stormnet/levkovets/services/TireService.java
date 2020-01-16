package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.TireDto;

import java.util.List;

public interface TireService extends DtoService<TireDto> {
    @Override
    TireDto getById(Integer id);

    @Override
    List<TireDto> getAll();

    @Override
    void delete(TireDto obj);

    @Override
    void saveOrUpdate(TireDto tireDto);
}
