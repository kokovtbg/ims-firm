package bg.sirma.ims.model.item;

import bg.sirma.ims.model.item.interfaces.Breakable;

import java.math.BigDecimal;

public class ElectronicItem extends InventoryItem implements Breakable {
    private int warranty;

    public ElectronicItem(String name,
                          String manufacturer,
                          String description,
                          ItemCategory category,
                          int quantity,
                          BigDecimal price,
                          int warranty) {
        super(name, manufacturer, description, category, quantity, price);
        this.warranty = warranty;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    @Override
    public BigDecimal value() {
        return getPrice().multiply(BigDecimal.valueOf(1.3));
    }

    @Override
    public String handleBreakage() {
        return String.format("%s is broken", this);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" warranty: (%d) years", this.warranty);
    }
}
