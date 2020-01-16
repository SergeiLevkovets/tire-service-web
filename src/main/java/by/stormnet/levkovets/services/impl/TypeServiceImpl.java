package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.TypeDao;
import by.stormnet.levkovets.dao.mysql.TypeDaoImpl;
import by.stormnet.levkovets.domain.impl.Type;
import by.stormnet.levkovets.dto.impl.TypeDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class TypeServiceImpl implements by.stormnet.levkovets.services.TypeService {

    @Override
    public TypeDto getById(Integer id) {
        TypeDao typeDao = new TypeDaoImpl();
        Type type = typeDao.loadById(id);
        TypeDto typeDto = EntityDtoConverter.transformToTypeDto(type);
        return typeDto;
    }

    @Override
    public TypeDto getTypeByName(String name) {
        TypeDao typeDao = new TypeDaoImpl();
        Type type = typeDao.loadByType(name);
        TypeDto typeDto = EntityDtoConverter.transformToTypeDto(type);
        return typeDto;
    }

    @Override
    public void delete(TypeDto obj) {
        TypeDao dao = new TypeDaoImpl();
        dao.delete(EntityDtoConverter.transformToTypeEntity(obj));
    }

    @Override
    public List<TypeDto> getAll() {
        TypeDao typeDao = new TypeDaoImpl();
        List<Type> types = typeDao.loadAll();
        List<TypeDto> typeDtoList = new ArrayList<>();
        for (Type type : types) {
            TypeDto typeDto = EntityDtoConverter.transformToTypeDto(type);
            typeDtoList.add(typeDto);
        }
        return typeDtoList;
    }

    @Override
    public void saveOrUpdate(TypeDto typeDto) {
        TypeDao typeDao = new TypeDaoImpl();

        if (typeDto.getId() == null) {
            typeDao.save(EntityDtoConverter.transformToTypeEntity(typeDto));
        }else {
            typeDao.update(EntityDtoConverter.transformToTypeEntity(typeDto));
        }
    }
}
