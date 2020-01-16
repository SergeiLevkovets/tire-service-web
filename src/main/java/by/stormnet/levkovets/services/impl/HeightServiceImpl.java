package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.HeightDao;
import by.stormnet.levkovets.dao.mysql.HeightDaoImpl;
import by.stormnet.levkovets.domain.impl.Height;
import by.stormnet.levkovets.dto.impl.HeightDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class HeightServiceImpl implements by.stormnet.levkovets.services.HeightService {
    
    @Override
    public HeightDto getById(Integer id) {
        HeightDao heightDao = new HeightDaoImpl();
        Height height = heightDao.loadById(id);
        HeightDto heightDto = EntityDtoConverter.transformToHeightDto(height);
        return heightDto;
    }

    @Override
    public HeightDto getHeightByName(String name) {
        HeightDao heightDao = new HeightDaoImpl();
        Height height = heightDao.loadBySize(name);
        HeightDto heightDto = EntityDtoConverter.transformToHeightDto(height);
        return heightDto;
    }

    @Override
    public void delete(HeightDto obj) {
        HeightDao dao = new HeightDaoImpl();
        dao.delete(EntityDtoConverter.transformToHeightEntity(obj));
    }

    @Override
    public List<HeightDto> getAll() {
        HeightDao heightDao = new HeightDaoImpl();
        List<Height> heightList = heightDao.loadAll();
        List<HeightDto> heightDtoList = new ArrayList<>();
        for (Height height : heightList) {
            HeightDto heightDto = EntityDtoConverter.transformToHeightDto(height);
            heightDtoList.add(heightDto);
        }
        return heightDtoList;
    }

    @Override
    public void saveOrUpdate(HeightDto heightDto) {
        HeightDao heightDao = new HeightDaoImpl();

        if (heightDto.getId() == null) {
            heightDao.save(EntityDtoConverter.transformToHeightEntity(heightDto));
        }else {
            heightDao.update(EntityDtoConverter.transformToHeightEntity(heightDto));
        }
    }
}
