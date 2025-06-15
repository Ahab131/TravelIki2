package model;

import java.sql.Date;
import enums.PesananStatus;

public class Pesanan {
    private int id;
    private int idPengguna;
    private Date tanggalPesanan;
    private PesananStatus status;

    public Pesanan(int id, int idPengguna, Date tanggalPesanan, PesananStatus status) {
        this.id = id;
        this.idPengguna = idPengguna;
        this.tanggalPesanan = tanggalPesanan;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    public Date getTanggalPesanan() {
        return tanggalPesanan;
    }

    public void setTanggalPesanan(Date tanggalPesanan) {
        this.tanggalPesanan = tanggalPesanan;
    }

    public PesananStatus getStatus() {
        return status;
    }

    public void setStatus(PesananStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pesanan{" +
                "id=" + id +
                ", idPengguna=" + idPengguna +
                ", tanggalPesanan=" + tanggalPesanan +
                '}';
    }
}
