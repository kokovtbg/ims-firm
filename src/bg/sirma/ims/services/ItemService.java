package bg.sirma.ims.services;

import bg.sirma.ims.exception.IOCustomException;
import bg.sirma.ims.exception.ItemNotFoundException;
import bg.sirma.ims.model.item.InventoryItem;
import bg.sirma.ims.model.item.ItemCategory;

import java.util.List;

public interface ItemService {
    InventoryItem add(InventoryItem item) throws IOCustomException;
    boolean remove(long id) throws ItemNotFoundException, IOCustomException;
    List<InventoryItem> getAll();
    InventoryItem getById(long id) throws ItemNotFoundException;
    InventoryItem update(long id, Number quantity) throws ItemNotFoundException, IOCustomException;
    List<InventoryItem> sortByName();
    List<InventoryItem> sortByCategory();
    List<InventoryItem> sortByPrice();
    List<InventoryItem> search(String name);
    List<InventoryItem> search(ItemCategory category);
    InventoryItem updateByClient(long id, Number quantity) throws ItemNotFoundException, IOCustomException;
}
