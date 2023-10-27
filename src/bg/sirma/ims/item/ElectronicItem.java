package bg.sirma.ims.item;

import bg.sirma.ims.item.interfaces.Breakable;

import java.math.BigDecimal;

public class ElectronicItem extends InventoryItem implements Breakable {
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
    public BigDecimal value() {
        return getPrice().multiply(BigDecimal.valueOf(1.3));
    }

    @Override
    public String handleBreakage() {
        return String.format("%s is broken", this);
    }

}
