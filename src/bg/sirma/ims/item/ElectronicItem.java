package bg.sirma.ims.item;

import java.math.BigDecimal;

public class ElectronicItem extends InventoryItem {
    private int warranty;

    public ElectronicItem(String name,
                          String manufacturer,
                          String description,
                          ItemCategory category,
                          long id,
                          int quantity,
                          BigDecimal price,
                          int warranty) {
        super(name, manufacturer, description, category, id, quantity, price);
        this.warranty = warranty;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    @Override
    public boolean isBreakable() {
        return true;
    }

    @Override
    public BigDecimal value() {
        return getPrice().multiply(BigDecimal.valueOf(1.3));
    }

    @Override
    public boolean isPerishable() {
        return false;
    }

}
