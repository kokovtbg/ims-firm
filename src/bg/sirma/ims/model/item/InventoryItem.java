package bg.sirma.ims.model.item;


import java.math.BigDecimal;
import java.util.Objects;

public class InventoryItem extends AbstractItem {
    private long id;
    private Number quantity;
    private BigDecimal price;

    public InventoryItem(String name,
                         String manufacturer,
                         String description,
                         ItemCategory category,
                         Number quantity,
                         BigDecimal price) {
        super(name, manufacturer, description, category);
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Number getQuantity() {
        return quantity;
    }

    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal value() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItem that = (InventoryItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
