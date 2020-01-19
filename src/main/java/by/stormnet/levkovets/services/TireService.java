package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.TireDto;

import java.util.List;

public interface TireService {
    TireDto getById(Integer id);

    List<TireDto> getAll();

    void delete(TireDto obj);

    Integer saveOrUpdate(TireDto tireDto);
}
