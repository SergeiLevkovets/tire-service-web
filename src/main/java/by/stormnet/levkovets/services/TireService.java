package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.TireDTO;

import java.util.List;

public interface TireService {
    TireDTO getById(Integer id);

    List<TireDTO> getAll();

    void delete(TireDTO obj);

    Integer saveOrUpdate(TireDTO tireDto);
}
