public class Tamu {
    private String nama;
    private int reservasi;

    public Tamu(String nama, int reservasi) {
        this.nama = nama;
        this.reservasi = reservasi;
    }

    public void buatReservasi() {
        System.out.println(nama + " membuat reservasi dengan ID " + reservasi);
    }

    public void batalReservasi() {
        System.out.println(nama + " membatalkan reservasi ID " + reservasi);
    }
}
