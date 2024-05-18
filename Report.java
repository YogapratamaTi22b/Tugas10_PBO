import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Report {
    private List<LocalDate> saleDates = new ArrayList<>();
    private Map<LocalDate, List<Sale>> salesByDate = new TreeMap<>();

    public void addSale(Sale sale) {
        saleDates.add(sale.getDate());
        if (!salesByDate.containsKey(sale.getDate())) {
            salesByDate.put(sale.getDate(), new ArrayList<>());
        }
        salesByDate.get(sale.getDate()).add(sale);
    }

    public List<LocalDate> getSaleDates() {
        return saleDates;
    }

    public double getTotalDailyRevenue(LocalDate date) {
        if (salesByDate.containsKey(date)) {
            double totalRevenue = 0;
            for (Sale sale : salesByDate.get(date)) {
                totalRevenue += sale.getRevenue();
            }
            return totalRevenue;
        } else {
            return 0;
        }
    }
}