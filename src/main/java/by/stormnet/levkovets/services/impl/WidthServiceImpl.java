package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.OrderDao;
import by.stormnet.levkovets.dao.mysql.WidthDao;
import by.stormnet.levkovets.domain.impl.Width;
import by.stormnet.levkovets.dto.impl.OrderDto;
import by.stormnet.levkovets.dto.impl.WidthDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class WidthServiceImpl implements DtoService<WidthDto> {

    @Override
    public WidthDto getById(Integer id) {
        WidthDao widthDao = new WidthDao();
        Width width = widthDao.loadById(id);
        WidthDto widthDto = EntityDtoConverter.transformToWidthDto(width);
        return widthDto;
    }

    public WidthDto getWidthByName(String name) {
        WidthDao widthDao = new WidthDao();
        Width width = widthDao.loadBySize(name);
        WidthDto widthDto = EntityDtoConverter.transformToWidthDto(width);
        return widthDto;
    }

    @Override
    public List<WidthDto> getAll() {
        WidthDao widthDao = new WidthDao();
        List<Width> widthList = widthDao.loadAll();
        List<WidthDto> widthDtoList = new ArrayList<>();
        for (Width width : widthList) {
            WidthDto widthDto = EntityDtoConverter.transformToWidthDto(width);
            widthDtoList.add(widthDto);
        }
        return widthDtoList;
    }

    @Override
    public void delete(WidthDto obj) {
        WidthDao dao = new WidthDao();
        dao.delete(EntityDtoConverter.transformToWidthEntity(obj));
    }

    @Override
    public void saveOrUpdate(WidthDto widthDto) {
        WidthDao widthDao = new WidthDao();

        if (widthDto.getId() == null) {
            widthDao.save(EntityDtoConverter.transformToWidthEntity(widthDto));
        }else {
            widthDao.update(EntityDtoConverter.transformToWidthEntity(widthDto));
        }
    }
}
