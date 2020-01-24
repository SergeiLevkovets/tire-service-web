package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.HeightDTO;

import java.util.List;

public interface HeightService {
    HeightDTO getById(Integer id);

    HeightDTO getByName(String name);

    void deleteById(Integer id);

    List<HeightDTO> getAll();

    void saveOrUpdate(HeightDTO heightDto);
}
