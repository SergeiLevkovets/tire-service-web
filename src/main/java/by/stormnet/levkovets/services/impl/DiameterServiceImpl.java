package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.DiameterDao;
import by.stormnet.levkovets.domain.impl.Diameter;
import by.stormnet.levkovets.dto.impl.DiameterDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class DiameterServiceImpl implements DtoService<DiameterDto> {

    @Override
    public DiameterDto getById(Integer id) {
        DiameterDao diameterDao = new DiameterDao();
        Diameter diameter = diameterDao.loadById(id);
        DiameterDto diameterDto = EntityDtoConverter.transformToDiameterDto(diameter);
        return diameterDto;
    }

    public DiameterDto getByName(String name) {
        DiameterDao diameterDao = new DiameterDao();
        Diameter diameter = diameterDao.loadBySize(name);
        DiameterDto diameterDto = EntityDtoConverter.transformToDiameterDto(diameter);
        return diameterDto;
    }

    @Override
    public List<DiameterDto> getAll() {
        DiameterDao diameterDao = new DiameterDao();
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
        DiameterDao diameterDao = new DiameterDao();

        if (diameterDto.getId() == null) {
            diameterDao.save(EntityDtoConverter.transformToDiameterEntity(diameterDto));
        }else {
            diameterDao.update(EntityDtoConverter.transformToDiameterEntity(diameterDto));
        }
    }
}
