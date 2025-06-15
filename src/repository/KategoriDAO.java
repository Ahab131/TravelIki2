package repository;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import model.Kategori;
import config.Database;

public class KategoriDAO {
    public List<Kategori> getAll() {
        List<Kategori> kategoriList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM kategori";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");

                Kategori kategori = new Kategori(id, nama);
                kategoriList.add(kategori);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return kategoriList;
    }

    public Kategori getById(int id) {
        Kategori kategori = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM kategori WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nama = resultSet.getString("nama");
                kategori = new Kategori(id, nama);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return kategori;
    }

    public Kategori getByNama(String nama) {
        Kategori kategori = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM kategori WHERE nama = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nama);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                kategori = new Kategori(id, nama);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return kategori;
    }

    public void create(String nama) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "INSERT INTO kategori (nama) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nama);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method untuk mengupdate kategori
    public void update(int id, String nama) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "UPDATE kategori SET nama = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nama);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method untuk menghapus kategori
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "DELETE FROM kategori WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
