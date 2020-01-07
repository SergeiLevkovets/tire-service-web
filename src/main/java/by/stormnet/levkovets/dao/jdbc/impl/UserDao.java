package by.stormnet.levkovets.dao.jdbc.impl;

import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.dao.jdbc.Dao;
import by.stormnet.levkovets.domain.impl.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {


    @Override
    public void save(User user) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.users (id, name, email, password, phone, role) VALUES (?, ?, ?, ?, ?, ?)");

            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getRole());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(User user) {
        Connection c = null;
        PreparedStatement statement = null;

        String email = "_NEEEEEW_ Email!!!";
        String password = "_NEEEEEW_ Password!!!";

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.users SET name = ?, email = ?, password = ?, phone = ?, role = ? WHERE id = ?");

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getRole());
            statement.setInt(6, user.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(User user) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.users WHERE id = ?");

            statement.setInt(1, user.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public User loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        User user = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, email, password, phone, role from tire_service_db.users where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                String email = set.getString("email");
                String password = set.getString("password");
                String phone = set.getString("phone");
                String role = set.getString("role");
                user = new User();
                user.setId(objectId);
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhone(phone);
                user.setRole(role);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return user;
    }

    @Override
    public List<User> loadAll() {
        List<User> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, email, password, phone, role from tire_service_db.users");
            set = statement.executeQuery();

            while (set.next()) {
                Integer id = set.getInt("id");
                String name = set.getString("name");
                String email = set.getString("email");
                String password = set.getString("password");
                String phone = set.getString("phone");
                String role = set.getString("role");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhone(phone);
                user.setRole(role);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}