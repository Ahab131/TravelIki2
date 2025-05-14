package enums;

public enum LayananStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String deskripsi;

    LayananStatus(String deskripsi) {
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
