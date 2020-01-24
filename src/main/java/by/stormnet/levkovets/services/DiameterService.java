package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.DiameterDTO;

import java.util.List;

public interface DiameterService {
    DiameterDTO getById(Integer id);

    DiameterDTO getByName(String name);

    void deleteById(Integer id);

    List<DiameterDTO> getAll();

    void saveOrUpdate(DiameterDTO diameterDto);
}
