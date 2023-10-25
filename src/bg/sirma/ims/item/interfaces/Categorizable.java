package bg.sirma.ims.item.interfaces;

import bg.sirma.ims.item.ItemCategory;

public interface Categorizable {
    ItemCategory getCategory();
    void setCategory(ItemCategory category);
}
