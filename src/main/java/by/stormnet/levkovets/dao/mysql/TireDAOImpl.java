package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.DiameterDAO;
import by.stormnet.levkovets.dao.HeightDAO;
import by.stormnet.levkovets.dao.TireDAO;
import by.stormnet.levkovets.dao.WidthDAO;
import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.domain.impl.Diameter;
import by.stormnet.levkovets.domain.impl.Height;
import by.stormnet.levkovets.domain.impl.Tire;
import by.stormnet.levkovets.domain.impl.Width;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

public class TireDAOImpl implements TireDAO {


    @Override
    public Integer save(Tire tire) {

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Integer id = null;

        try {
            c = ConnectionManager.getManager().getConnection();


            statement = c.prepareStatement("INSERT INTO tire_service_db.tires (fk_width_id, fk_height_id, fk_diameter_id) VALUES (?, ?, ?)");

            statement.setInt(1, tire.getWidth().getId());
            statement.setInt(2, tire.getHeight().getId());
            if (tire.getDiameter() == null) {
                statement.setNull(3, NULL);
            } else {
                statement.setInt(3, tire.getDiameter().getId());
            }

            statement.executeUpdate();

            set = statement.executeQuery("SELECT LAST_INSERT_ID()");

            while (set.next()) {
                id = set.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }

        return id;
    }

    @Override
    public void update(Tire tire) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.tires SET fk_width_id = ?, fk_height_id = ?, fk_diameter_id = ?, date = ? WHERE id = ?");

            statement.setInt(1, tire.getWidth().getId());
            statement.setInt(2, tire.getHeight().getId());
            if (tire.getDiameter() == null) {
                statement.setNull(3, NULL);
            } else {
                statement.setInt(3, tire.getDiameter().getId());
            }
            statement.setTimestamp(4, new Timestamp(tire.getDate().getTime()));
            statement.setInt(5, tire.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.tires WHERE id = ?");

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public Tire loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Tire tire = null;
        WidthDAO widthDao = new WidthDAOImpl();
        HeightDAO heightDao = new HeightDAOImpl();
        DiameterDAO diameterDao = new DiameterDAOImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, fk_width_id, fk_height_id, fk_diameter_id, `date` from tire_service_db.tires where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                Width width = widthDao.loadById(set.getInt("fk_width_id"));
                Height height = heightDao.loadById(set.getInt("fk_height_id"));
                Diameter diameter = diameterDao.loadById(set.getInt("fk_diameter_id"));
                java.util.Date date = set.getTimestamp("date");
                tire = new Tire();
                tire.setId(objectId);
                tire.setWidth(width);
                tire.setHeight(height);
                tire.setDiameter(diameter);
                tire.setDate(date);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return tire;
    }

    @Override
    public List<Tire> loadAll() {
        List<Tire> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        WidthDAO widthDao = new WidthDAOImpl();
        HeightDAO heightDao = new HeightDAOImpl();
        DiameterDAO diameterDao = new DiameterDAOImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, fk_width_id, fk_height_id, fk_diameter_id, `date` from tire_service_db.tires");
            set = statement.executeQuery();


            while (set.next()) {
                Integer objectId = set.getInt("id");
                Width width = widthDao.loadById(set.getInt("fk_width_id"));
                Height height = heightDao.loadById(set.getInt("fk_height_id"));
                Diameter diameter = diameterDao.loadById(set.getInt("fk_diameter_id"));
                java.util.Date date = set.getTimestamp("date");
                Tire tire = new Tire();
                tire.setId(objectId);
                tire.setWidth(width);
                tire.setHeight(height);
                tire.setDiameter(diameter);
                tire.setDate(date);
                list.add(tire);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }

    @Override
    public List<Tire> loadCountTOP(Integer count) {

        List<Tire> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        WidthDAO widthDao = new WidthDAOImpl();
        HeightDAO heightDao = new HeightDAOImpl();
        DiameterDAO diameterDao = new DiameterDAOImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("SELECT fk_width_id, fk_height_id, fk_diameter_id FROM tires GROUP BY fk_width_id, fk_height_id, fk_diameter_id ORDER BY COUNT(*) DESC LIMIT ?");
            statement.setInt(1, count);
            set = statement.executeQuery();


            while (set.next()) {
                Width width = widthDao.loadById(set.getInt("fk_width_id"));
                Height height = heightDao.loadById(set.getInt("fk_height_id"));
                Diameter diameter = diameterDao.loadById(set.getInt("fk_diameter_id"));
                Tire tire = new Tire();
                tire.setWidth(width);
                tire.setHeight(height);
                tire.setDiameter(diameter);
                list.add(tire);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}