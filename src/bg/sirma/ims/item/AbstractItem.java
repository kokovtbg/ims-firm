package bg.sirma.ims.item;


import bg.sirma.ims.item.interfaces.*;

public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable {
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
    public String handleBreakage() {
        return String.format("%s is broken", this);
    }

    @Override
    public String handleExpiration() {
        return String.format("%s has expired", this);
    }

    @Override
    public String toString() {
        return String.format("Item with name: (%s) and manufacturer: (%s)", this.name, this.manufacturer);
    }
}
