package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.HeightDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.Height;
import by.stormnet.levkovets.dto.impl.HeightDTO;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class HeightServiceImpl implements by.stormnet.levkovets.services.HeightService {
    
    @Override
    public HeightDTO getById(Integer id) {
        HeightDAO heightDao = DAOFactory.getFactory().getHeightDAO();
        Height height = heightDao.loadById(id);
        HeightDTO heightDto = EntityDtoConverter.transformToHeightDto(height);
        return heightDto;
    }

    @Override
    public HeightDTO getByName(String name) {
        HeightDAO heightDao = DAOFactory.getFactory().getHeightDAO();
        Height height = heightDao.loadBySize(name);
        HeightDTO heightDto = EntityDtoConverter.transformToHeightDto(height);
        return heightDto;
    }

    @Override
    public void delete(HeightDTO obj) {
        HeightDAO dao = DAOFactory.getFactory().getHeightDAO();
        dao.delete(EntityDtoConverter.transformToHeightEntity(obj));
    }

    @Override
    public List<HeightDTO> getAll() {
        HeightDAO heightDao = DAOFactory.getFactory().getHeightDAO();
        List<Height> heightList = heightDao.loadAll();
        List<HeightDTO> heightDtoList = new ArrayList<>();
        for (Height height : heightList) {
            HeightDTO heightDto = EntityDtoConverter.transformToHeightDto(height);
            heightDtoList.add(heightDto);
        }
        return heightDtoList;
    }

    @Override
    public void saveOrUpdate(HeightDTO heightDto) {
        HeightDAO heightDao = DAOFactory.getFactory().getHeightDAO();

        if (heightDto.getId() == null) {
            heightDao.save(EntityDtoConverter.transformToHeightEntity(heightDto));
        }else {
            heightDao.update(EntityDtoConverter.transformToHeightEntity(heightDto));
        }
    }
}
