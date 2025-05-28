package service;

import java.util.List;

import dao.KategoriDAO;
import model.Kategori;

public class KategoriService {
    private final KategoriDAO kategoriDAO;

    public KategoriService(KategoriDAO kategoriDAO) {
        this.kategoriDAO = kategoriDAO;
    }

    public List<Kategori> getAll() {
        // Validasi input
        if (kategoriDAO == null) {
            throw new IllegalArgumentException("[ KategoriDAO tidak boleh null ]");
        }

        List<Kategori> kategoriList = kategoriDAO.getAll();

        if (kategoriList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada kategori yang ditemukan ]");
        }

        return kategoriList;
    }

    public Kategori getById(int id) {
        Kategori kategori = kategoriDAO.getById(id);
        if (kategori == null) {
            throw new IllegalArgumentException("[ Kategori tidak ditemukan ]");
        }

        return kategori;
    }

    public Kategori getByNama(String nama) {
        // Validasi input
        if (nama == null || nama.isEmpty()) {
            throw new IllegalArgumentException("[ Nama tidak boleh kosong ]");
        }

        Kategori kategori = kategoriDAO.getByNama(nama);
        if (kategori == null) {
            throw new IllegalArgumentException("[ Kategori tidak ditemukan ]");
        }

        return kategori;
    }

    public void create(String kategori) {
        kategoriDAO.create(kategori);
    }

    // validasi duplikat kategori
    public void validateDuplicate(String kategori) {
        if (kategoriDAO.getByNama(kategori) != null) {
            throw new IllegalArgumentException("[ Kategori sudah ada ]");
        }
    }
}
