package service;

import java.util.List;

import dao.LayananDAO;
import model.Layanan;
import enums.LayananStatus;

public class LayananService {
    private final LayananDAO layananDAO;
    private final KategoriDAO kategoriDAO;

    public LayananService(LayananDAO layananDAO) {
        this.layananDAO = layananDAO;
    }

    public List<Layanan> getAll() {
        // Validasi input
        if (layananDAO == null) {
            throw new IllegalArgumentException("[ LayananDAO tidak boleh null ]");
        }

        List<Layanan> layananList = layananDAO.getAll();

        if (layananList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada layanan yang ditemukan ]");
        }

        return layananList;
    }

    public Layanan getById(int id) {
        // Validasi input
        if (id <= 0) {
            throw new IllegalArgumentException("[ ID tidak valid ]");
        }

        Layanan layanan = layananDAO.getById(id);
        if (layanan == null) {
            throw new IllegalArgumentException("[ Layanan tidak ditemukan ]");
        }

        return layanan;
    }

    public Layanan getByNama(String nama) {
        // Validasi input
        if (nama == null || nama.isEmpty()) {
            throw new IllegalArgumentException("[ Nama tidak boleh kosong ]");
        }

        Layanan layanan = layananDAO.getByNama(nama);
        if (layanan == null) {
            throw new IllegalArgumentException("[ Layanan tidak ditemukan ]");
        }

        return layanan;
    }

    public List<Layanan> getByKategori(String kategori) { // Kategori diambil dari id kategori
        // Validasi input
        if (kategori == null || kategori.isEmpty()) {
            throw new IllegalArgumentException("[ Kategori tidak boleh kosong ]");
        }

        List<Layanan> layananList = layananDAO.getByKategori(kategori); // Kategori diambil dari id kategori
        if (layananList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada layanan dengan kategori " + kategori + " ]");
        }

        return layananList;
    }

    public List<Layanan> getByStatus(String status) {
        // Validasi input
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("[ Status tidak boleh kosong ]");
        }

        // Validasi status
        if (!status.equals(LayananStatus.ACTIVE.toString()) && !status.equals(LayananStatus.INACTIVE.toString())) {
            throw new IllegalArgumentException("[ Status tidak valid ]");
        }

        List<Layanan> layananList = layananDAO.getByStatus(status);
        if (layananList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada layanan dengan status " + status + " ]");
        }

        return layananList;
    }

    public void create(String nama, String deskripsi, double harga, int idKategori, String status) {
        // Validasi input
        validateInput(nama, kategoriDAO.getById(idKategori).getNama(), status);
        validateDuplicate(nama);

        Layanan layanan = new Layanan(0, nama, deskripsi, harga, idKategori, LayananStatus.valueOf(status));
        layananDAO.create(layanan);
    }

    public void update(int id, String nama, String deskripsi, double harga, int idKategori, String status) {
        // Validasi input
        validateInput(nama, kategoriDAO.getById(idKategori).getNama(), status);

        Layanan layanan = new Layanan(id, nama, deskripsi, harga, idKategori, LayananStatus.valueOf(status));
        layananDAO.update(layanan);
    }

    public void delete(int id) {
        // Validasi input
        if (id <= 0) {
            throw new IllegalArgumentException("[ ID tidak valid ]");
        }

        Layanan layanan = layananDAO.getById(id);
        if (layanan == null) {
            throw new IllegalArgumentException("[ Layanan tidak ditemukan ]");
        }

        layananDAO.delete(id);
    }

    // Validasi input
    public void validateInput(String nama, String kategori, String status) {
        // Validasi input
        if (nama == null || nama.isEmpty()) {
            throw new IllegalArgumentException("[ Nama tidak boleh kosong ]");
        }
        if (kategori == null || kategori.isEmpty()) { // Kategori diambil dari id kategori
            throw new IllegalArgumentException("[ Kategori tidak boleh kosong ]");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("[ Status tidak boleh kosong ]");
        }

        // Validasi status
        if (!status.equals(LayananStatus.ACTIVE.toString()) && !status.equals(LayananStatus.INACTIVE.toString())) {
            throw new IllegalArgumentException("[ Status tidak valid ]");
        }
    }

    // validasi jika nama layanan sudah ada
    public void validateDuplicate(String nama) {
        if(layananDAO.getByNama(nama) != null) {
            throw new IllegalArgumentException("[ Nama layanan sudah ada ]");
        }
    }
}
