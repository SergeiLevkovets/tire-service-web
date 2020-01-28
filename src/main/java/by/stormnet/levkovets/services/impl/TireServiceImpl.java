package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.TireDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.Tire;
import by.stormnet.levkovets.dto.impl.DiameterDTO;
import by.stormnet.levkovets.dto.impl.HeightDTO;
import by.stormnet.levkovets.dto.impl.TireDTO;
import by.stormnet.levkovets.dto.impl.WidthDTO;
import by.stormnet.levkovets.services.TireService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;
import by.stormnet.levkovets.services.factory.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class TireServiceImpl implements by.stormnet.levkovets.services.TireService {



    @Override
    public TireDTO getById(Integer id) {

        TireDAO tireDaoImpl = DAOFactory.getFactory().getTireDAO();

        TireDTO tireDto = EntityDtoConverter.transformToTireDto(tireDaoImpl.loadById(id));

        return tireDto;
    }

    @Override
    public List<TireDTO> getAll() {
        TireDAO tireDaoImpl = DAOFactory.getFactory().getTireDAO();
        List<TireDTO> list = new ArrayList<>();

        for (Tire tire : tireDaoImpl.loadAll()) {
            TireDTO tireDto = EntityDtoConverter.transformToTireDto(tire);
            list.add(tireDto);
        }

        return list;
    }

    @Override
    public void deleteById(Integer id) {
        TireDAO dao = DAOFactory.getFactory().getTireDAO();
        dao.deleteById(id);
    }

    @Override
    public Integer saveOrUpdate(TireDTO tireDto) {
        TireDAO tireDaoImpl = DAOFactory.getFactory().getTireDAO();
        Integer id = tireDto.getId();
        if (id == null){
            id = tireDaoImpl.save(EntityDtoConverter.transformToTireEntity(tireDto));
        }else {
            tireDaoImpl.update(EntityDtoConverter.transformToTireEntity(tireDto));
        }
        return id;
    }

    @Override
    public TireDTO createTireDTO(WidthDTO widthDto, HeightDTO heightDto, DiameterDTO diameterDto){

        TireService tireService = ServiceFactory.getFactory().getTireService();

        TireDTO tireDto = new TireDTO();

        tireDto.setWidth(widthDto);
        tireDto.setHeight(heightDto);
        tireDto.setDiameter(diameterDto);

        Integer tireId = tireService.saveOrUpdate(tireDto);

        tireDto.setId(tireId);

        return tireDto;
    }

    @Override
    public List<TireDTO> loadCountTOP(Integer count) {
        TireDAO tireDaoImpl = DAOFactory.getFactory().getTireDAO();
        List<TireDTO> list = new ArrayList<>();

        for (Tire tire : tireDaoImpl.loadCountTOP(count)) {
            TireDTO tireDto = EntityDtoConverter.transformToTireDto(tire);
            list.add(tireDto);
        }

        return list;
    }


}
