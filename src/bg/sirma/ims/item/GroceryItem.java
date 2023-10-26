package bg.sirma.ims.item;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GroceryItem extends InventoryItem {
    private final LocalDate expirationDate;
    public GroceryItem(String name,
                       String manufacturer,
                       String description,
                       ItemCategory category,
                       long id,
                       Number quantity,
                       BigDecimal price,
                       LocalDate expirationDate) {
        super(name, manufacturer, description, category, id, quantity, price);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isBreakable() {
        return false;
    }

    @Override
    public BigDecimal value() {
        return this.getPrice().multiply(BigDecimal.valueOf(1.2));
    }

    @Override
    public boolean isPerishable() {
        return true;
    }

}
