package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.WidthDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.Width;
import by.stormnet.levkovets.dto.impl.WidthDTO;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class WidthServiceImpl implements by.stormnet.levkovets.services.WidthService {

    @Override
    public WidthDTO getById(Integer id) {
        WidthDAO widthDao = DAOFactory.getFactory().getWidthDAO();
        Width width = widthDao.loadById(id);
        WidthDTO widthDto = EntityDtoConverter.transformToWidthDto(width);
        return widthDto;
    }

    @Override
    public WidthDTO getByName(String name) {
        WidthDAO widthDao = DAOFactory.getFactory().getWidthDAO();
        Width width = widthDao.loadBySize(name);
        WidthDTO widthDto = EntityDtoConverter.transformToWidthDto(width);
        return widthDto;
    }

    @Override
    public List<WidthDTO> getAll() {
        WidthDAO widthDao = DAOFactory.getFactory().getWidthDAO();
        List<Width> widthList = widthDao.loadAll();
        List<WidthDTO> widthDtoList = new ArrayList<>();
        for (Width width : widthList) {
            WidthDTO widthDto = EntityDtoConverter.transformToWidthDto(width);
            widthDtoList.add(widthDto);
        }
        return widthDtoList;
    }

    @Override
    public void delete(WidthDTO obj) {
        WidthDAO dao = DAOFactory.getFactory().getWidthDAO();
        dao.delete(EntityDtoConverter.transformToWidthEntity(obj));
    }

    @Override
    public void saveOrUpdate(WidthDTO widthDto) {
        WidthDAO widthDao = DAOFactory.getFactory().getWidthDAO();

        if (widthDto.getId() == null) {
            widthDao.save(EntityDtoConverter.transformToWidthEntity(widthDto));
        } else {
            widthDao.update(EntityDtoConverter.transformToWidthEntity(widthDto));
        }
    }
}
