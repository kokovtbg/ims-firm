package bg.sirma.ims.services;

import bg.sirma.ims.exception.*;
import bg.sirma.ims.model.item.InventoryItem;
import bg.sirma.ims.model.item.ItemCategory;

import java.util.List;

public interface ItemService {
    InventoryItem add(InventoryItem item) throws IOCustomException, PermissionDeniedException, ItemNotValidException;
    boolean remove(long id) throws ItemNotFoundException, IOCustomException, PermissionDeniedException;
    List<InventoryItem> getAll();
    InventoryItem getById(long id) throws ItemNotFoundException, PermissionDeniedException;
    InventoryItem update(long id, String quantity) throws ItemNotFoundException, IOCustomException, PermissionDeniedException, ItemNotValidException;
    List<InventoryItem> sortByName();
    List<InventoryItem> sortByCategory();
    List<InventoryItem> sortByPrice();
    List<InventoryItem> search(String name);
    List<InventoryItem> search(ItemCategory category);
    InventoryItem updateByClient(long id, Number quantity) throws ItemNotFoundException, IOCustomException, ItemNotValidException, ItemQuantityNotEnoughException;
}
