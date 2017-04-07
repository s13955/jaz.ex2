package pja.jaz.persistance;

import pja.jaz.model.Role;
import pja.jaz.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HSQLUserRepository implements UserRepository {

    private static Connection connection;

    public HSQLUserRepository() {

        try {
            if (null == connection) {
                connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "SA", "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void add(User user) {
        if (null == user) {
            throw new IllegalArgumentException("Null value was given!");
        }

        try {
            PreparedStatement stmt = connection
                    .prepareStatement("INSERT INTO client(username, password, email, role) VALUES(?, ?, ?, ?);");

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, String.valueOf(user.getRole()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM client WHERE username = ?");
            stmt.setString(1, user.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement stmt = connection.
                    prepareStatement("UPDATE client SET password = ?, email = ?, role = ? WHERE username = ?");

            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, String.valueOf(user.getRole()));
            stmt.setString(4, user.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUsername(String username) {
        User user = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM client WHERE username = ?");
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(username, rs.getString("password"), rs.getString("email"),
                        Role.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM client");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                list.add(new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), Role.valueOf(rs.getString("role"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
