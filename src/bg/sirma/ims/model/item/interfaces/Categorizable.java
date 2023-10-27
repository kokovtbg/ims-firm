package bg.sirma.ims.model.item.interfaces;

import bg.sirma.ims.model.item.ItemCategory;

public interface Categorizable {
    ItemCategory getCategory();
    void setCategory(ItemCategory category);
}
