package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.DiameterDAO;
import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.domain.impl.Diameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiameterDAOImpl implements DiameterDAO {


    @Override
    public void save(Diameter diameter) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.diameters (diameter) VALUES (?)");

            statement.setString(1, diameter.getDiameter());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(Diameter diameter) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.diameters SET diameter = ? WHERE id = ?");

            statement.setString(1, diameter.getDiameter());
            statement.setInt(2, diameter.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(Diameter diameter) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.diameters WHERE id = ?");

            statement.setInt(1, diameter.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public Diameter loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Diameter diameter = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, diameter from tire_service_db.diameters where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String diameterField = set.getString("diameter");
                diameter = new Diameter();
                diameter.setId(objectId);
                diameter.setDiameter(diameterField);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return diameter;
    }

    @Override
    public List<Diameter> loadAll() {
        List<Diameter> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, diameter from tire_service_db.diameters");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String diameterField = set.getString("diameter");
                Diameter diameter = new Diameter();
                diameter.setId(objectId);
                diameter.setDiameter(diameterField);
                list.add(diameter);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }

    @Override
    public Diameter loadBySize(String size) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Diameter diameter = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, diameter from tire_service_db.diameters where diameter = ?");
            statement.setString(1, size);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String diameterField = set.getString("diameter");
                diameter = new Diameter();
                diameter.setId(objectId);
                diameter.setDiameter(diameterField);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return diameter;
    }
}