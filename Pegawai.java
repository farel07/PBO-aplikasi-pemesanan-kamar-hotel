public class Pegawai extends User {

    public Pegawai(String username, String password) {
        super(username, password);
    }

    public void checkin() {
        System.out.println("Proses check-in.");
    }

    public void checkout() {
        System.out.println("Proses check-out.");
    }

    public void kelolaTamu() {
        System.out.println("Kelola tamu.");
    }

    public void kelolaRestoran() {
        System.out.println("Kelola restoran.");
    }

    public void pembayaran() {
        System.out.println("Proses pembayaran.");
    }
}
