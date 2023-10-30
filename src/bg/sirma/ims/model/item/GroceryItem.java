package bg.sirma.ims.model.item;

import bg.sirma.ims.model.item.interfaces.Perishable;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GroceryItem extends InventoryItem implements Perishable {
    private final LocalDate expirationDate;

    public GroceryItem(String name,
                       String manufacturer,
                       String description,
                       ItemCategory category,
                       double quantity,
                       BigDecimal price,
                       LocalDate expirationDate) {
        super(name, manufacturer, description, category, quantity, price);
        this.expirationDate = expirationDate;
    }

    public GroceryItem(String name,
                       String manufacturer,
                       String description,
                       ItemCategory category,
                       int quantityPerPiece,
                       BigDecimal price,
                       LocalDate expirationDate) {
        super(name, manufacturer, description, category, quantityPerPiece, price);
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

    @Override
    public String toString() {
        return super.toString() + String.format(" expiration date: (%s)", this.expirationDate);
    }
}
