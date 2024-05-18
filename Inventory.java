import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Drug> drugs;

    public Inventory() {
        drugs = new ArrayList<>();
    }

    public void addDrug(Drug drug) {
        drugs.add(drug);
    }

    public void editDrug(Drug drug, double newPrice, int newQuantity) {
        int index = drugs.indexOf(drug);
        if (index == -1) {
            return;
        }
        drugs.get(index).setPrice(newPrice);
        drugs.get(index).setQuantity(newQuantity);
    }

    public Drug getDrugById(int id) {
        for (Drug drug : drugs) {
            if (drug.getId() == id) {
                return drug;
            }
        }
        return null;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public int getQuantity(Drug drug) {
        int index = drugs.indexOf(drug);
        if (index == -1) {
            return 0;
        }
        return drugs.get(index).getQuantity();
    }

    public void deductQuantity(Drug drug, int quantity) throws InventoryException {
        int index = drugs.indexOf(drug);
        if (index == -1) {
            throw new InventoryException("Drug not found in inventory");
        }
        int currentQuantity = drugs.get(index).getQuantity();
        if (currentQuantity < quantity) {
            throw new InventoryException("Insufficient quantity in inventory");
        }
        drugs.get(index).setQuantity(currentQuantity - quantity);
    }

    public Drug getDrugByName(String nameEdit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDrugByName'");
    }
}