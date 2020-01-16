package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.HeightDto;

import java.util.List;

public interface HeightService extends DtoService<HeightDto> {
    @Override
    HeightDto getById(Integer id);

    HeightDto getHeightByName(String name);

    @Override
    void delete(HeightDto obj);

    @Override
    List<HeightDto> getAll();

    @Override
    void saveOrUpdate(HeightDto heightDto);
}
