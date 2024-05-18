import java.util.ArrayList;
import java.util.List;

public class MedicineStore extends Inventory {
    public String name;
    public List<Medicine> medicines;

    public MedicineStore(String name) {
        this.name = name;
        this.medicines = new ArrayList<>();
    }

    public void addMedicine(Medicine medicine) {
        this.medicines.add(medicine);
    }

    public void removeMedicine(Medicine medicine) {
        this.medicines.remove(medicine);
    }

    public List<Medicine> getMedicines() {
        return this.medicines;
    }

    public String getName() {
        return this.name;
    }
}