package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.OrderDao;
import by.stormnet.levkovets.dao.mysql.TypeDao;
import by.stormnet.levkovets.domain.impl.Type;
import by.stormnet.levkovets.dto.impl.OrderDto;
import by.stormnet.levkovets.dto.impl.TypeDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class TypeServiceImpl implements DtoService<TypeDto> {

    @Override
    public TypeDto getById(Integer id) {
        TypeDao typeDao = new TypeDao();
        Type type = typeDao.loadById(id);
        TypeDto typeDto = EntityDtoConverter.transformToTypeDto(type);
        return typeDto;
    }

    public TypeDto getTypeByName(String name) {
        TypeDao typeDao = new TypeDao();
        Type type = typeDao.loadByType(name);
        TypeDto typeDto = EntityDtoConverter.transformToTypeDto(type);
        return typeDto;
    }

    @Override
    public void delete(TypeDto obj) {
        TypeDao dao = new TypeDao();
        dao.delete(EntityDtoConverter.transformToTypeEntity(obj));
    }

    @Override
    public List<TypeDto> getAll() {
        TypeDao typeDao = new TypeDao();
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
        TypeDao typeDao = new TypeDao();

        if (typeDto.getId() == null) {
            typeDao.save(EntityDtoConverter.transformToTypeEntity(typeDto));
        }else {
            typeDao.update(EntityDtoConverter.transformToTypeEntity(typeDto));
        }
    }
}
