package bg.sirma.ims.model.item;

import bg.sirma.ims.model.item.interfaces.Breakable;

import java.math.BigDecimal;

public class FragileItem extends InventoryItem implements Breakable {
    private final double weight;
    public FragileItem(String name,
                       String manufacturer,
                       String description,
                       ItemCategory category,
                       int quantity,
                       BigDecimal price,
                       double weight) {
        super(name, manufacturer, description, category, quantity, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public BigDecimal value() {
        return this.getPrice().multiply(BigDecimal.valueOf(1.4));
    }

    @Override
    public String handleBreakage() {
        return String.format("%s is broken", this);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" weight: (%s)", this.weight);
    }
}
