package bg.sirma.ims.item;

import java.math.BigDecimal;

public class FragileItem extends InventoryItem {
    private final double weight;
    public FragileItem(String name,
                       String manufacturer,
                       String description,
                       ItemCategory category,
                       long id,
                       int quantity,
                       double weight) {
        super(name, manufacturer, description, category, id, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return true;
    }

    @Override
    public BigDecimal value() {
        return this.getPrice().multiply(BigDecimal.valueOf(1.4));
    }

    @Override
    public boolean isPerishable() {
        return false;
    }
}
