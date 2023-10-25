package bg.sirma.ims.item;


import java.math.BigDecimal;

public abstract class InventoryItem extends AbstractItem {
    private long id;
    private Number quantity;
    private BigDecimal price;

    public InventoryItem(String name,
                         String manufacturer,
                         String description,
                         ItemCategory category,
                         long id,
                         Number quantity) {
        super(name, manufacturer, description, category);
        this.id = id;
        this.quantity = quantity;
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
}
