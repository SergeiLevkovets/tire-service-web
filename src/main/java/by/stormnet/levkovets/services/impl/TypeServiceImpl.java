package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.TypeDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.Type;
import by.stormnet.levkovets.dto.impl.TypeDTO;
import by.stormnet.levkovets.services.TypeService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class TypeServiceImpl implements TypeService {

    @Override
    public TypeDTO getById(Integer id) {
        TypeDAO typeDao = DAOFactory.getFactory().getTypeDAO();
        Type type = typeDao.loadById(id);
        TypeDTO typeDto = EntityDtoConverter.transformToTypeDto(type);
        return typeDto;
    }

    @Override
    public TypeDTO getByName(String name) {
        TypeDAO typeDao = DAOFactory.getFactory().getTypeDAO();
        Type type = typeDao.loadByType(name);
        TypeDTO typeDto = EntityDtoConverter.transformToTypeDto(type);
        return typeDto;
    }

    @Override
    public void deleteById(Integer id) {
        TypeDAO dao = DAOFactory.getFactory().getTypeDAO();
        dao.deleteById(id);
    }

    @Override
    public List<TypeDTO> getAll() {
        TypeDAO typeDao = DAOFactory.getFactory().getTypeDAO();
        List<Type> types = typeDao.loadAll();
        List<TypeDTO> typeDtoList = new ArrayList<>();
        for (Type type : types) {
            TypeDTO typeDto = EntityDtoConverter.transformToTypeDto(type);
            typeDtoList.add(typeDto);
        }
        return typeDtoList;
    }

    @Override
    public void saveOrUpdate(TypeDTO typeDto) {
        TypeDAO typeDao = DAOFactory.getFactory().getTypeDAO();

        if (typeDto.getId() == null) {
            typeDao.save(EntityDtoConverter.transformToTypeEntity(typeDto));
        }else {
            typeDao.update(EntityDtoConverter.transformToTypeEntity(typeDto));
        }
    }
}
