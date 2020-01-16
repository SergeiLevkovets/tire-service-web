package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.DiameterDao;
import by.stormnet.levkovets.dao.mysql.DiameterDaoImpl;
import by.stormnet.levkovets.domain.impl.Diameter;
import by.stormnet.levkovets.dto.impl.DiameterDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class DiameterServiceImpl implements by.stormnet.levkovets.services.DiameterService {

    @Override
    public DiameterDto getById(Integer id) {
        DiameterDao diameterDao = new DiameterDaoImpl();
        Diameter diameter = diameterDao.loadById(id);
        DiameterDto diameterDto = EntityDtoConverter.transformToDiameterDto(diameter);
        return diameterDto;
    }

    @Override
    public DiameterDto getByName(String name) {
        DiameterDao diameterDao = new DiameterDaoImpl();
        Diameter diameter = diameterDao.loadBySize(name);
        DiameterDto diameterDto = EntityDtoConverter.transformToDiameterDto(diameter);
        return diameterDto;
    }

    @Override
    public void delete(DiameterDto obj) {
        DiameterDao dao = new DiameterDaoImpl();
        dao.delete(EntityDtoConverter.transformToDiameterEntity(obj));
    }

    @Override
    public List<DiameterDto> getAll() {
        DiameterDao diameterDao = new DiameterDaoImpl();
        List<Diameter> diameterList = diameterDao.loadAll();
        List<DiameterDto> diameterDtoList = new ArrayList<>();
        for (Diameter diameter : diameterList) {
            DiameterDto diameterDto = EntityDtoConverter.transformToDiameterDto(diameter);
            diameterDtoList.add(diameterDto);
        }
        return diameterDtoList;
    }

    @Override
    public void saveOrUpdate(DiameterDto diameterDto) {
        DiameterDao diameterDao = new DiameterDaoImpl();

        if (diameterDto.getId() == null) {
            diameterDao.save(EntityDtoConverter.transformToDiameterEntity(diameterDto));
        }else {
            diameterDao.update(EntityDtoConverter.transformToDiameterEntity(diameterDto));
        }
    }
}
