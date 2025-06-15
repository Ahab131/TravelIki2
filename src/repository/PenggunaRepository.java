package repository;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import model.Pengguna;
import config.Database;
import enums.UserRole;

public class PenggunaRepository {

    // public static void main(String[] args) {
    //     PenggunaDAO penggunaDAO = new PenggunaDAO();

    //     penggunaDAO.getAll().forEach(System.out::println);
    // }

    public List<Pengguna> getAll() {
        List<Pengguna> penggunaList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM pengguna";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id_pengguna");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String roleString = resultSet.getString("role");
                UserRole role = UserRole.valueOf(roleString.toUpperCase());
                
                Pengguna pengguna = new Pengguna(id, username, email, password, role);
                penggunaList.add(pengguna);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return penggunaList;
    }

    public Pengguna getById(int id) {
        Pengguna pengguna = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM pengguna WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String roleString = resultSet.getString("role");
                UserRole role = UserRole.valueOf(roleString.toUpperCase());

                pengguna = new Pengguna(id, username, email, password, role);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pengguna;
    }

    public Pengguna getByUsername(String username) {
        Pengguna pengguna = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM pengguna WHERE username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String roleString = resultSet.getString("role");
                UserRole role = UserRole.valueOf(roleString.toUpperCase());

                pengguna = new Pengguna(id, username, email, password, role);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pengguna;
    }

    public Pengguna getByEmail(String email) {
        Pengguna pengguna = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM pengguna WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String roleString = resultSet.getString("role");
                UserRole role = UserRole.valueOf(roleString.toUpperCase());

                pengguna = new Pengguna(id, username, email, password, role);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pengguna;
    }

    public List<Pengguna> getByRole(String role) {
        List<Pengguna> penggunaList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM pengguna WHERE role = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                UserRole userRole = UserRole.valueOf(role.toUpperCase());

                Pengguna pengguna = new Pengguna(id, username, email, password, userRole);
                penggunaList.add(pengguna);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return penggunaList;
    }

    public void create(Pengguna pengguna) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "INSERT INTO pengguna (username, email, password, role) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pengguna.getUsername());
            preparedStatement.setString(2, pengguna.getEmail());
            preparedStatement.setString(3, pengguna.getPassword());
            preparedStatement.setString(4, pengguna.getRole().toString());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Pengguna pengguna) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "UPDATE pengguna SET username = ?, email = ?, password = ?, role = ? WHERE id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pengguna.getUsername());
            preparedStatement.setString(2, pengguna.getEmail());
            preparedStatement.setString(3, pengguna.getPassword());
            preparedStatement.setString(4, pengguna.getRole().toString());
            preparedStatement.setInt(5, pengguna.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "DELETE FROM pengguna WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}