public class Manager extends User {

    public Manager(String username, String password) {
        super(username, password);
    }

    public void tambahDataKamar() {
        System.out.println("Tambah data kamar.");
    }

    public void updateDataKamar() {
        System.out.println("Update data kamar.");
    }

    public void hapusDataKamar() {
        System.out.println("Hapus data kamar.");
    }

    public void lihatDataKamar() {
        System.out.println("Lihat data kamar.");
    }

    public void tambahDataMenu() {
        System.out.println("Tambah data menu.");
    }

    public void updateDataMenu() {
        System.out.println("Update data menu.");
    }

    public void hapusDataMenu() {
        System.out.println("Hapus data menu.");
    }

    public void lihatDataMenu() {
        System.out.println("Lihat data menu.");
    }
}
