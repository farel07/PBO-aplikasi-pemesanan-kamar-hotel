public class Pembayaran {
    private int idPembayaran;
    private int jumlah;
    private String metode;
    private int tipe;

    public Pembayaran(int idPembayaran, int jumlah, String metode, int tipe) {
        this.idPembayaran = idPembayaran;
        this.jumlah = jumlah;
        this.metode = metode;
        this.tipe = tipe;
    }

    public void prosesPembayaran() {
        System.out.println("Proses pembayaran ID " + idPembayaran + " sejumlah " + jumlah + " via " + metode);
    }
}
