package bg.sirma.ims.model.item;


import java.math.BigDecimal;

public class InventoryItem extends AbstractItem {
    private long id;
    private Number quantity;
    private BigDecimal price;

    public InventoryItem(String name,
                         String manufacturer,
                         String description,
                         ItemCategory category,
                         long id,
                         Number quantity,
                         BigDecimal price) {
        super(name, manufacturer, description, category);
        this.id = id;
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

}
