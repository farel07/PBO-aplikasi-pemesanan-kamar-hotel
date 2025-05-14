public class Kamar {
    private int nomor;
    private String tipe;
    private int status;

    public Kamar(int nomor, String tipe, int status) {
        this.nomor = nomor;
        this.tipe = tipe;
        this.status = status;
    }

    public void updateStatus(int status) {
        this.status = status;
        System.out.println("Status kamar " + nomor + " diperbarui ke " + status);
    }
}
