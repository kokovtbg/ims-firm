package bg.sirma.ims.item;

import bg.sirma.ims.item.interfaces.Perishable;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GroceryItem extends InventoryItem implements Perishable {
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
    public BigDecimal value() {
        return this.getPrice().multiply(BigDecimal.valueOf(1.2));
    }

    @Override
    public String handleExpiration() {
        return String.format("%s has expired", this);
    }

}
