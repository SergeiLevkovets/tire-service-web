package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.TypeDAO;
import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.domain.impl.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDAOImpl implements TypeDAO {


    @Override
    public void save(Type type) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.types (type) VALUES (?)");

            statement.setString(1, type.getType());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(Type type) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.types SET type = ? WHERE id = ?");

            statement.setString(1, type.getType());
            statement.setInt(2, type.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
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
            statement = c.prepareStatement("DELETE FROM tire_service_db.types WHERE id = ?");

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public Type loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Type type = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, type from tire_service_db.types where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String typeField = set.getString("type");
                type = new Type();
                type.setId(objectId);
                type.setType(typeField);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return type;
    }

    @Override
    public List<Type> loadAll() {
        List<Type> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, type from tire_service_db.types");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String typeField = set.getString("type");
                Type type = new Type();
                type.setId(objectId);
                type.setType(typeField);
                list.add(type);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }

    @Override
    public Type loadByType(String type) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Type typeElem = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, type from tire_service_db.types where type = ?");
            statement.setString(1, type);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String typeField = set.getString("type");
                typeElem = new Type();
                typeElem.setId(objectId);
                typeElem.setType(typeField);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return typeElem;
    }
}