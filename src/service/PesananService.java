package service;

import java.util.List;
import java.sql.Date;

import model.Pesanan;
import repository.PesananRepository;
import enums.PesananStatus;

public class PesananService {
    private final PesananRepository pesananDAO;

    public PesananService(PesananRepository pesananDAO) {
        this.pesananDAO = pesananDAO;
    }

    public List<Pesanan> getAll() {
        // Validasi input
        if (pesananDAO == null) {
            throw new IllegalArgumentException("[ PesananDAO tidak boleh null ]");
        }

        List<Pesanan> pesananList = pesananDAO.getAll();

        if (pesananList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada pesanan yang ditemukan ]");
        }

        return pesananList;
    }

    public Pesanan getById(int id) {
        Pesanan pesanan = pesananDAO.getById(id);
        if (pesanan == null) {
            throw new IllegalArgumentException("[ Pesanan tidak ditemukan ]");
        }

        return pesanan;
    }

    public List<Pesanan> getByIdPengguna(int idPengguna) {
        // Validasi input
        if (idPengguna <= 0) {
            throw new IllegalArgumentException("[ ID Pengguna tidak valid ]");
        }

        List<Pesanan> pesananList = pesananDAO.getByIdPengguna(idPengguna);
        if (pesananList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada pesanan yang ditemukan untuk pengguna ini ]");
        }

        return pesananList;
    }
    
    public List<Pesanan> getByStatus(PesananStatus status) {
        // Validasi input
        if (status == null) {
            throw new IllegalArgumentException("[ Status tidak boleh kosong ]");
        }

        List<Pesanan> pesananList = pesananDAO.getByStatus(status);
        if (pesananList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada pesanan dengan status ini ]");
        }

        return pesananList;
    }

    public void create(int idPengguna, Date tanggalPesanan, PesananStatus status) {
        // Validasi input
        validateInput(idPengguna, tanggalPesanan, status);

        Pesanan pesanan = new Pesanan(0, idPengguna, tanggalPesanan, status);
        pesananDAO.create(pesanan);
    }

    public void update(int id, int idPengguna, Date tanggalPesanan, PesananStatus status) {
        // Validasi input
        validateInput(idPengguna, tanggalPesanan, status);

        Pesanan pesanan = new Pesanan(id, idPengguna, tanggalPesanan, status);
        pesananDAO.update(pesanan);
    }

    public void delete(int id) {
        Pesanan pesanan = pesananDAO.getById(id);
        if (pesanan == null) {
            throw new IllegalArgumentException("[ Pesanan tidak ditemukan ]");
        }

        pesananDAO.delete(id);
    }

    public void validateInput(int idPengguna, Date tanggalPesanan, PesananStatus status) {
        if (idPengguna <= 0) {
            throw new IllegalArgumentException("[ ID Pengguna tidak valid ]");
        }

        if (tanggalPesanan == null) {
            throw new IllegalArgumentException("[ Tanggal Pesanan tidak boleh kosong ]");
        }

        if (status == null) {
            throw new IllegalArgumentException("[ Status tidak boleh kosong ]");
        }
    }
}
