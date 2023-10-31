package bg.sirma.ims.model.item;


import java.math.BigDecimal;
import java.util.Objects;

public class InventoryItem extends AbstractItem {
    private long id;
    private Double quantityPerKilogram;
    private Integer quantityPerPiece;
    private BigDecimal price;
    private String type;

    public InventoryItem(String name,
                         String manufacturer,
                         String description,
                         ItemCategory category,
                         double quantity,
                         BigDecimal price) {
        super(name, manufacturer, description, category);
        this.quantityPerKilogram = quantity;
        this.price = price;
        this.type = "InventoryItem";
    }

    public InventoryItem(String name,
                         String manufacturer,
                         String description,
                         ItemCategory category,
                         int quantityPerPiece,
                         BigDecimal price) {
        super(name, manufacturer, description, category);
        this.quantityPerPiece = quantityPerPiece;
        this.price = price;
        this.type = "InventoryItem";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getQuantityPerKilogram() {
        return quantityPerKilogram;
    }

    public void setQuantityPerKilogram(double quantityPerKilogram) {
        this.quantityPerKilogram = quantityPerKilogram;
    }

    public Integer getQuantityPerPiece() {
        return quantityPerPiece;
    }

    public void setQuantityPerPiece(int quantityPerPiece) {
        this.quantityPerPiece = quantityPerPiece;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        String add = (quantityPerPiece != null) ?
                String.format(" with quantity: (%d), price: (%s)", this.quantityPerPiece, this.price) :
                String.format(" with quantity (%s), price: (%s)", this.quantityPerKilogram, this.price);
        return super.toString() + add;
    }
}
