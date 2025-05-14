public class Restoran {
    private String nama;
    private int kapasitas;

    public Restoran(String nama, int kapasitas) {
        this.nama = nama;
        this.kapasitas = kapasitas;
    }

    public void kelolaAksesTamu() {
        System.out.println("Kelola akses tamu ke restoran " + nama);
    }

    public void cekReservasi() {
        System.out.println("Cek reservasi untuk restoran " + nama);
    }
}
