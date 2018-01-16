package components;

/**
 * Created by OANY on 1/14/2018.
 */
public class Stock {
    private String stockId;
    private int quantity;

    public Stock(String stockId, int quantity){
        this.stockId = stockId;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
