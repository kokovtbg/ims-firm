package bg.sirma.ims.model.item;


import bg.sirma.ims.model.item.interfaces.*;

public abstract class AbstractItem implements Item, Categorizable, Sellable {
    private String name;
    private String manufacturer;
    private String description;
    private ItemCategory category;

    public AbstractItem(String name, String manufacturer, String description, ItemCategory category) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
        this.category = category;
    }

    @Override
    public String details() {
        return this.toString();
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public ItemCategory getCategory() {
        return category;
    }

    @Override
    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Item with name: (%s), category: (%s) and manufacturer: (%s)",
                this.name, this.category, this.manufacturer);
    }
}
