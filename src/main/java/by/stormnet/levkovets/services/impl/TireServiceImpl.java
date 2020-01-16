package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.TireDaoImpl;
import by.stormnet.levkovets.domain.impl.Tire;
import by.stormnet.levkovets.dto.impl.TireDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class TireServiceImpl implements by.stormnet.levkovets.services.TireService {
    @Override
    public TireDto getById(Integer id) {
        TireDaoImpl tireDaoImpl = new TireDaoImpl();

        TireDto tireDto = EntityDtoConverter.transformToTireDto(tireDaoImpl.loadById(id));

        return tireDto;
    }

    @Override
    public List<TireDto> getAll() {
        TireDaoImpl tireDaoImpl = new TireDaoImpl();
        List<TireDto> list = new ArrayList<>();

        for (Tire tire : tireDaoImpl.loadAll()) {
            TireDto tireDto = EntityDtoConverter.transformToTireDto(tire);
            list.add(tireDto);
        }

        return list;
    }

    @Override
    public void delete(TireDto obj) {
        TireDaoImpl dao = new TireDaoImpl();
        dao.delete(EntityDtoConverter.transformToTireEntity(obj));
    }

    @Override
    public void saveOrUpdate(TireDto tireDto) {
        TireDaoImpl tireDaoImpl = new TireDaoImpl();
        if (tireDto.getId() == null){
            tireDaoImpl.save(EntityDtoConverter.transformToTireEntity(tireDto));
        }else {
            tireDaoImpl.update(EntityDtoConverter.transformToTireEntity(tireDto));
        }
    }
}
