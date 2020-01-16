package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.WidthDto;

import java.util.List;

public interface WidthService extends DtoService<WidthDto> {
    @Override
    WidthDto getById(Integer id);

    WidthDto getWidthByName(String name);

    @Override
    List<WidthDto> getAll();

    @Override
    void delete(WidthDto obj);

    @Override
    void saveOrUpdate(WidthDto widthDto);
}
