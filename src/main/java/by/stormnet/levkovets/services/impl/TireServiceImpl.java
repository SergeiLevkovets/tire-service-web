package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.OrderDao;
import by.stormnet.levkovets.dao.mysql.TireDao;
import by.stormnet.levkovets.domain.impl.Tire;
import by.stormnet.levkovets.dto.impl.OrderDto;
import by.stormnet.levkovets.dto.impl.TireDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class TireServiceImpl implements DtoService<TireDto> {
    @Override
    public TireDto getById(Integer id) {
        TireDao tireDao = new TireDao();

        TireDto tireDto = EntityDtoConverter.transformToTireDto(tireDao.loadById(id));

        return tireDto;
    }

    @Override
    public List<TireDto> getAll() {
        TireDao tireDao = new TireDao();
        List<TireDto> list = new ArrayList<>();

        for (Tire tire : tireDao.loadAll()) {
            TireDto tireDto = EntityDtoConverter.transformToTireDto(tire);
            list.add(tireDto);
        }

        return list;
    }

    @Override
    public void delete(TireDto obj) {
        TireDao dao = new TireDao();
        dao.delete(EntityDtoConverter.transformToTireEntity(obj));
    }

    @Override
    public void saveOrUpdate(TireDto tireDto) {
        TireDao tireDao = new TireDao();
        if (tireDto.getId() == null){
            tireDao.save(EntityDtoConverter.transformToTireEntity(tireDto));
        }else {
            tireDao.update(EntityDtoConverter.transformToTireEntity(tireDto));
        }
    }
}
