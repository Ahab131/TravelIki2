package model;

import java.sql.Date;
import enums.PembayaranStatus;

public class Pembayaran {
    private int id;
    private int idPesanan;
    private double total;
    private double bayar;
    private Date tanggalPembayaran;
    private PembayaranStatus status;

    public Pembayaran(int id, int idPesanan, double total, double bayar, Date tanggalPembayaran, PembayaranStatus status) {
        this.id = id;
        this.idPesanan = idPesanan;
        this.total = total;
        this.bayar = bayar;
        this.tanggalPembayaran = tanggalPembayaran;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(int idPesanan) {
        this.idPesanan = idPesanan;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getBayar() {
        return bayar;
    }

    public void setBayar(double bayar) {
        this.bayar = bayar;
    }

    public Date getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(Date tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public PembayaranStatus getStatus() {
        return status;
    }

    public void setStatus(PembayaranStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pembayaran{" +
                "id=" + id +
                ", idPesanan=" + idPesanan +
                ", total=" + total +
                ", bayar=" + bayar +
                ", tanggalPembayaran=" + tanggalPembayaran +
                ", status=" + status +
                '}';
    }
}
