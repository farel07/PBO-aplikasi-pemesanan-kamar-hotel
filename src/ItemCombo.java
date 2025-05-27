public class ItemCombo {
    private int id;
    private String label;

    public ItemCombo(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label; // agar tampil di JComboBox
    }
}
