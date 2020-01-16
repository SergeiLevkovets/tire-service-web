package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.DiameterDao;
import by.stormnet.levkovets.dao.mysql.HeightDao;
import by.stormnet.levkovets.domain.impl.Height;
import by.stormnet.levkovets.dto.impl.DiameterDto;
import by.stormnet.levkovets.dto.impl.HeightDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class HeightServiceImpl implements DtoService<HeightDto> {
    
    @Override
    public HeightDto getById(Integer id) {
        HeightDao heightDao = new HeightDao();
        Height height = heightDao.loadById(id);
        HeightDto heightDto = EntityDtoConverter.transformToHeightDto(height);
        return heightDto;
    }

    public HeightDto getHeightByName(String name) {
        HeightDao heightDao = new HeightDao();
        Height height = heightDao.loadBySize(name);
        HeightDto heightDto = EntityDtoConverter.transformToHeightDto(height);
        return heightDto;
    }

    @Override
    public void delete(HeightDto obj) {
        HeightDao dao = new HeightDao();
        dao.delete(EntityDtoConverter.transformToHeightEntity(obj));
    }

    @Override
    public List<HeightDto> getAll() {
        HeightDao heightDao = new HeightDao();
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
        HeightDao heightDao = new HeightDao();

        if (heightDto.getId() == null) {
            heightDao.save(EntityDtoConverter.transformToHeightEntity(heightDto));
        }else {
            heightDao.update(EntityDtoConverter.transformToHeightEntity(heightDto));
        }
    }
}
