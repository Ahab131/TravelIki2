package service;

import java.util.List;

import model.Pengguna;
import repository.PenggunaRepository;
import enums.UserRole;

public class PenggunaService {
    private final PenggunaRepository penggunaDAO;

    public PenggunaService(PenggunaRepository penggunaDAO) {
        this.penggunaDAO = penggunaDAO;
    }

    public List<Pengguna> getAll() {
        // Validasi input
        if (penggunaDAO == null) {
            throw new IllegalArgumentException("[ PenggunaDAO tidak boleh null ]");
        }
        
        List<Pengguna> penggunaList = penggunaDAO.getAll();

        if (penggunaList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada pengguna yang ditemukan ]");
        }

        return penggunaList;
    }

    public Pengguna getById(int id) {
        // Validasi input
        if (id <= 0) {
            throw new IllegalArgumentException("[ ID tidak valid ]");
        }

        Pengguna pengguna = penggunaDAO.getById(id);
        if (pengguna == null) {
            throw new IllegalArgumentException("[ Pengguna tidak ditemukan ]");
        }

        return pengguna;
    }

    public Pengguna getByUsername(String username) {
        // Validasi input
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("[ Username tidak boleh kosong ]");
        }

        Pengguna pengguna = penggunaDAO.getByUsername(username);
        if (pengguna == null) {
            throw new IllegalArgumentException("[ Pengguna tidak ditemukan ]");
        }

        return pengguna;
    }

    public Pengguna getByEmail(String email) {
        // Validasi input
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("[ Email tidak boleh kosong ]");
        }

        Pengguna pengguna = penggunaDAO.getByEmail(email);
        if (pengguna == null) {
            throw new IllegalArgumentException("[ Pengguna tidak ditemukan ]");
        }

        return pengguna;
    }

    public List<Pengguna> getByRole(String role) {
        // Validasi input
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("[ Role tidak boleh kosong ]");
        }
        
        // Validasi role
        if (!UserRole.valueOf(role.toUpperCase()).equals(UserRole.ADMIN) &&
                !UserRole.valueOf(role.toUpperCase()).equals(UserRole.EMPLOYEE) &&
                !UserRole.valueOf(role.toUpperCase()).equals(UserRole.CUSTOMER)) {
            throw new IllegalArgumentException("[ Role tidak valid ]");
        }

        List<Pengguna> penggunaList = penggunaDAO.getByRole(role);
        if (penggunaList.isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada pengguna dengan role " + role + " ]");
        }

        return penggunaList;
    }

    public void create(String username, String email, String password, String role) {
        // Validasi input
        validateInput(username, email, password, role);

        // Validasi jika username, email, dan role sudah terdaftar
        validateDuplicate(username, email, role);

        // Buat pengguna baru
        Pengguna pengguna = new Pengguna(1, username, email, password, UserRole.valueOf(role.toUpperCase()));

        // Simpan pengguna ke database
        penggunaDAO.create(pengguna);

        // Validasi jika pengguna berhasil disimpan
        if (pengguna.getId() <= 0) {
            throw new IllegalArgumentException("[ Gagal menambahkan pengguna ]");
        } else {
            System.out.println("[ Pengguna berhasil ditambahkan ]");
        }
    }

    public void update(int id, String username, String email, String password, String role) {
        // Validasi input
        validateInput(username, email, password, role);

        // Validasi jika pengguna tidak ditemukan
        Pengguna pengguna = penggunaDAO.getById(id);
        if (pengguna == null) {
            throw new IllegalArgumentException("[ Pengguna tidak ditemukan ]");
        }

        // Validasi jika username, email, dan role sudah terdaftar
        validateDuplicate(username, email, role);

        // Update pengguna
        pengguna.setUsername(username);
        pengguna.setEmail(email);
        pengguna.setPassword(password);
        pengguna.setRole(UserRole.valueOf(role.toUpperCase()));

        // Simpan pengguna ke database
        penggunaDAO.update(pengguna);

        // Validasi jika pengguna berhasil diupdate
        if (pengguna.getId() <= 0) {
            throw new IllegalArgumentException("[ Gagal mengupdate pengguna ]");
        } else {
            System.out.println("[ Pengguna berhasil diupdate ]");
        }        
    }

    public void delete(int id) {
        // Validasi input
        if (id <= 0) {
            throw new IllegalArgumentException("[ ID tidak valid ]");
        }

        // Validasi jika pengguna tidak ditemukan
        Pengguna pengguna = penggunaDAO.getById(id);
        if (pengguna == null) {
            throw new IllegalArgumentException("[ Pengguna tidak ditemukan ]");
        }

        // Hapus pengguna dari database
        penggunaDAO.delete(id);

        // Validasi jika pengguna berhasil dihapus
        if (penggunaDAO.getById(id) != null) {
            throw new IllegalArgumentException("[ Gagal menghapus pengguna ]");
        } else {
            System.out.println("[ Pengguna berhasil dihapus ]");
        }
    }

    // validasi input
    public void validateInput(String username, String email, String password, String role) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("[ Username tidak boleh kosong ]");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("[ Email tidak boleh kosong ]");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("[ Password tidak boleh kosong ]");
        }
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("[ Role tidak boleh kosong ]");
        }
        if (!UserRole.valueOf(role.toUpperCase()).equals(UserRole.ADMIN) &&
                !UserRole.valueOf(role.toUpperCase()).equals(UserRole.EMPLOYEE) &&
                !UserRole.valueOf(role.toUpperCase()).equals(UserRole.CUSTOMER)) {
            throw new IllegalArgumentException("[ Role tidak valid ]");
        }
    }

    // validasi jika username, email, dan role sudah terdaftar
    public void validateDuplicate(String username, String email, String role) {
        if (penggunaDAO.getByUsername(username) != null) {
            throw new IllegalArgumentException("[ Username sudah terdaftar ]");
        }
        if (penggunaDAO.getByEmail(email) != null) {
            throw new IllegalArgumentException("[ Email sudah terdaftar ]");
        }
        if (penggunaDAO.getByRole(role) != null) {
            throw new IllegalArgumentException("[ Role sudah terdaftar ]");
        }
    }
}
