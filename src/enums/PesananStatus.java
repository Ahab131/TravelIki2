package enums;

public enum PesananStatus {
    PENDING("Pending"), // menunggu pembayaran
    CANCELLED("Cancelled"),
    COMPLETED("Completed");

    private final String deskripsi;

    PesananStatus(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    @Override
    public String toString() {
        return deskripsi;
    }
}
