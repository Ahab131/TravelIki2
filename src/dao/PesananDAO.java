package dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import model.Pesanan;
import config.Database;
import enums.PesananStatus;

public class PesananDAO {
    public List<Pesanan> getAll() {
        List<Pesanan> pesananList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM pesanan";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idPengguna = resultSet.getInt("id_pengguna");
                Date tanggalPesanan = resultSet.getDate("tanggal_pesanan");
                PesananStatus status = PesananStatus.valueOf(resultSet.getString("status"));

                Pesanan pesanan = new Pesanan(id, idPengguna, tanggalPesanan, status);
                pesananList.add(pesanan);
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

        return pesananList;
    }

    public Pesanan getById(int id) {
        Pesanan pesanan = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM pesanan WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idPengguna = resultSet.getInt("id_pengguna");
                Date tanggalPesanan = resultSet.getDate("tanggal_pesanan");
                PesananStatus status = PesananStatus.valueOf(resultSet.getString("status"));

                pesanan = new Pesanan(id, idPengguna, tanggalPesanan, status);
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

        return pesanan;
    }

    public List<Pesanan> getByIdPengguna(int idPengguna) {
        List<Pesanan> pesananList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM pesanan WHERE id_pengguna = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idPengguna);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date tanggalPesanan = resultSet.getDate("tanggal_pesanan");
                PesananStatus status = PesananStatus.valueOf(resultSet.getString("status"));

                Pesanan pesanan = new Pesanan(id, idPengguna, tanggalPesanan, status);
                pesananList.add(pesanan);
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

        return pesananList;
    }

    public List<Pesanan> getByStatus(PesananStatus status) {
        List<Pesanan> pesananList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "SELECT * FROM pesanan WHERE status = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status.toString());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idPengguna = resultSet.getInt("id_pengguna");
                Date tanggalPesanan = resultSet.getDate("tanggal_pesanan");

                Pesanan pesanan = new Pesanan(id, idPengguna, tanggalPesanan, status);
                pesananList.add(pesanan);
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

        return pesananList;
    }

    public void create(Pesanan pesanan) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "INSERT INTO pesanan (id_pengguna, tanggal_pesanan, status) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pesanan.getIdPengguna());
            preparedStatement.setDate(2, new java.sql.Date(pesanan.getTanggalPesanan().getTime()));
            preparedStatement.setString(3, pesanan.getStatus().toString());
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

    public void update(Pesanan pesanan) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getInstance().getConnection();
            String sql = "UPDATE pesanan SET id_pengguna = ?, tanggal_pesanan = ?, status = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pesanan.getIdPengguna());
            preparedStatement.setDate(2, new java.sql.Date(pesanan.getTanggalPesanan().getTime()));
            preparedStatement.setString(3, pesanan.getStatus().toString());
            preparedStatement.setInt(4, pesanan.getId());
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
            String sql = "DELETE FROM pesanan WHERE id = ?";
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
