package dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import model.Layanan;
import config.Database;
import enums.LayananStatus;

public class LayananDAO {
    public List<Layanan> getAll() {
        List<Layanan> layananList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM layanan";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String deskripsi = resultSet.getString("deskripsi");
                double harga = resultSet.getDouble("harga");
                int idKategori = resultSet.getInt("id_kategori");
                String statusString = resultSet.getString("status");
                LayananStatus status = LayananStatus.valueOf(statusString.toUpperCase());

                Layanan layanan = new Layanan(id, nama, deskripsi, harga, idKategori, status);
                layananList.add(layanan);
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
        return layananList;
    }

    public Layanan getById(int id) {
        Layanan layanan = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM layanan WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nama = resultSet.getString("nama");
                String deskripsi = resultSet.getString("deskripsi");
                double harga = resultSet.getDouble("harga");
                int idKategori = resultSet.getInt("id_kategori");
                String statusString = resultSet.getString("status");
                LayananStatus status = LayananStatus.valueOf(statusString.toUpperCase());

                layanan = new Layanan(id, nama, deskripsi, harga, idKategori, status);
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
        return layanan;
    }

    public Layanan getByNama(String nama) {
        Layanan layanan = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM layanan WHERE nama = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nama);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String deskripsi = resultSet.getString("deskripsi");
                double harga = resultSet.getDouble("harga");
                int idKategori = resultSet.getInt("id_kategori");
                String statusString = resultSet.getString("status");
                LayananStatus status = LayananStatus.valueOf(statusString.toUpperCase());

                layanan = new Layanan(id, nama, deskripsi, harga, idKategori, status);
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
        return layanan;
    }

    public List<Layanan> getByKategori(int idKategori) {
        List<Layanan> layananList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM layanan WHERE id_kategori = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idKategori);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String deskripsi = resultSet.getString("deskripsi");
                double harga = resultSet.getDouble("harga");
                String statusString = resultSet.getString("status");
                LayananStatus status = LayananStatus.valueOf(statusString.toUpperCase());

                Layanan layanan = new Layanan(id, nama, deskripsi, harga, idKategori, status);
                layananList.add(layanan);
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
        return layananList;
    }

    public List<Layanan> getByStatus(String status) {
        List<Layanan> layananList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM layanan WHERE status = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status.toString());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String deskripsi = resultSet.getString("deskripsi");
                double harga = resultSet.getDouble("harga");
                int idKategori = resultSet.getInt("id_kategori");
                LayananStatus layananStatus = LayananStatus.valueOf(status.toUpperCase());

                Layanan layanan = new Layanan(id, nama, deskripsi, harga, idKategori, layananStatus);
                layananList.add(layanan);
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
        return layananList;
    }

    public void create(Layanan layanan) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "INSERT INTO layanan (nama, deskripsi, harga, id_kategori, status) VALUES (?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, layanan.getNama());
            preparedStatement.setString(2, layanan.getDeskripsi());
            preparedStatement.setDouble(3, layanan.getHarga());
            preparedStatement.setInt(4, layanan.getIdKategori());
            preparedStatement.setString(5, layanan.getStatus().toString());

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

    public void update(Layanan layanan) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "UPDATE layanan SET nama = ?, deskripsi = ?, harga = ?, id_kategori = ?, status = ? WHERE id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, layanan.getNama());
            preparedStatement.setString(2, layanan.getDeskripsi());
            preparedStatement.setDouble(3, layanan.getHarga());
            preparedStatement.setInt(4, layanan.getIdKategori());
            preparedStatement.setString(5, layanan.getStatus().toString());
            preparedStatement.setInt(6, layanan.getId());

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

    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "DELETE FROM layanan WHERE id = ?";
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
