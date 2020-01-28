package by.stormnet.levkovets.services;

import by.stormnet.levkovets.domain.impl.Tire;
import by.stormnet.levkovets.dto.impl.DiameterDTO;
import by.stormnet.levkovets.dto.impl.HeightDTO;
import by.stormnet.levkovets.dto.impl.TireDTO;
import by.stormnet.levkovets.dto.impl.WidthDTO;

import java.util.List;

public interface TireService {
    TireDTO getById(Integer id);

    List<TireDTO> getAll();

    void deleteById(Integer id);

    Integer saveOrUpdate(TireDTO tireDto);

    TireDTO createTireDTO(WidthDTO widthDto, HeightDTO heightDto, DiameterDTO diameterDto);

    List<TireDTO> loadCountTOP(Integer count);
}
