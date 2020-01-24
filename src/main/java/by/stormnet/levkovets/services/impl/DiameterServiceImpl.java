package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.DiameterDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.Diameter;
import by.stormnet.levkovets.dto.impl.DiameterDTO;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class DiameterServiceImpl implements by.stormnet.levkovets.services.DiameterService {

    @Override
    public DiameterDTO getById(Integer id) {
        DiameterDAO diameterDao = DAOFactory.getFactory().getDiameterDAO();
        Diameter diameter = diameterDao.loadById(id);
        DiameterDTO diameterDto = EntityDtoConverter.transformToDiameterDto(diameter);
        return diameterDto;
    }

    @Override
    public DiameterDTO getByName(String name) {
        DiameterDAO diameterDao = DAOFactory.getFactory().getDiameterDAO();
        Diameter diameter = diameterDao.loadBySize(name);
        DiameterDTO diameterDto = EntityDtoConverter.transformToDiameterDto(diameter);
        return diameterDto;
    }

    @Override
    public void deleteById(Integer id) {
        DiameterDAO dao = DAOFactory.getFactory().getDiameterDAO();
        dao.deleteById(id);
    }

    @Override
    public List<DiameterDTO> getAll() {
        DiameterDAO diameterDao = DAOFactory.getFactory().getDiameterDAO();
        List<Diameter> diameterList = diameterDao.loadAll();
        List<DiameterDTO> diameterDtoList = new ArrayList<>();
        for (Diameter diameter : diameterList) {
            DiameterDTO diameterDto = EntityDtoConverter.transformToDiameterDto(diameter);
            diameterDtoList.add(diameterDto);
        }
        return diameterDtoList;
    }

    @Override
    public void saveOrUpdate(DiameterDTO diameterDto) {
        DiameterDAO diameterDao = DAOFactory.getFactory().getDiameterDAO();

        if (diameterDto.getId() == null) {
            diameterDao.save(EntityDtoConverter.transformToDiameterEntity(diameterDto));
        }else {
            diameterDao.update(EntityDtoConverter.transformToDiameterEntity(diameterDto));
        }
    }
}
