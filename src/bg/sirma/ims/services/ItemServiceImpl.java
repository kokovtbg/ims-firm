package bg.sirma.ims.services;

import bg.sirma.ims.exception.*;
import bg.sirma.ims.fileHandlers.MyFileHandler;
import bg.sirma.ims.model.item.AbstractItem;
import bg.sirma.ims.model.item.InventoryItem;
import bg.sirma.ims.model.item.ItemCategory;
import bg.sirma.ims.model.user.RoleEnum;
import bg.sirma.ims.model.user.User;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static bg.sirma.ims.constants.Constants.itemsPath;

public class ItemServiceImpl implements ItemService {
    private boolean checkAdminPrivileges() {
        User currentUser = UserServiceImpl.getCurrentUser();
        return currentUser != null &&
                (currentUser.getRoles().contains(RoleEnum.ADMIN)
                        || currentUser.getRoles().contains(RoleEnum.MODERATOR));
    }

    private void validateItem(InventoryItem item) throws ItemNotValidException {
        if (item.getName().isBlank() || item.getManufacturer().isBlank() || item.getDescription().isBlank()
                || item.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ItemNotValidException("Item must be valid!!!");
        }

        if (item.getQuantityPerKilogram() != null && item.getQuantityPerKilogram() <= 0
                || item.getQuantityPerPiece() != null && item.getQuantityPerPiece() <= 0) {
            throw new ItemNotValidException("Item must be valid");
        }
    }

    private void checkAvailability(InventoryItem item, Number quantity) throws ItemQuantityNotEnoughException {
        if (item.getQuantityPerPiece() - (int) quantity < 0) {
            throw new ItemQuantityNotEnoughException(String.format("Not enough quantity from item (%s)!!!", item));
        }

        if (item.getQuantityPerKilogram() - (double) quantity < 0) {
            throw new ItemQuantityNotEnoughException(String.format("Not enough quantity from item (%s)!!!", item));
        }

    }

    @Override
    public InventoryItem add(InventoryItem item) throws IOCustomException, PermissionDeniedException, ItemNotValidException {
        if (!checkAdminPrivileges()) {
            throw new PermissionDeniedException("You do not have permissions for that!!!");
        }

        validateItem(item);

        List<InventoryItem> items = MyFileHandler.getAllFromFile(itemsPath, InventoryItem[].class);
        long lastId = MyFileHandler.getLastId(items);
        item.setId(lastId + 1);
        items.add(item);
        MyFileHandler.saveToFile(items, itemsPath);

        return item;
    }

    @Override
    public boolean remove(long id) throws ItemNotFoundException, IOCustomException, PermissionDeniedException {
        if (!checkAdminPrivileges()) {
            throw new PermissionDeniedException("You do not have permissions for that!!!");
        }

        List<InventoryItem> items = MyFileHandler.getAllFromFile(itemsPath, InventoryItem[].class);
        InventoryItem inventoryItem = items.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException(String.format("Item with id (%d) not found!!!", id)));
        items.remove(inventoryItem);
        MyFileHandler.saveToFile(items, itemsPath);

        return true;
    }

    @Override
    public List<InventoryItem> getAll() {
        return MyFileHandler.getAllFromFile(itemsPath, InventoryItem[].class);
    }

    @Override
    public InventoryItem getById(long id) throws ItemNotFoundException {
        List<InventoryItem> items = MyFileHandler.getAllFromFile(itemsPath, InventoryItem[].class);

        return items.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException(String.format("Item with id (%d) not found!!!", id)));
    }

    @Override
    public InventoryItem update(long id, String quantity) throws ItemNotFoundException, IOCustomException, PermissionDeniedException, ItemNotValidException {
        if (!checkAdminPrivileges()) {
            throw new PermissionDeniedException("You do not have permissions for that!!!");
        }

        List<InventoryItem> items = MyFileHandler.getAllFromFile(itemsPath, InventoryItem[].class);
        InventoryItem inventoryItem = getById(id);

        if (inventoryItem.getQuantityPerKilogram() != null) {
            inventoryItem.setQuantityPerKilogram(Double.parseDouble(quantity));
        } else if (inventoryItem.getQuantityPerPiece() != null) {
            inventoryItem.setQuantityPerPiece(Integer.parseInt(quantity));
        }

        validateItem(inventoryItem);

        MyFileHandler.saveToFile(items, itemsPath);

        return inventoryItem;
    }

    @Override
    public List<InventoryItem> sortByName() {
        List<InventoryItem> items = MyFileHandler.getAllFromFile(itemsPath, InventoryItem[].class);

        return items.stream()
                .sorted(Comparator.comparing(AbstractItem::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryItem> sortByCategory() {
        List<InventoryItem> items = MyFileHandler.getAllFromFile(itemsPath, InventoryItem[].class);

        return items.stream()
                .sorted(Comparator.comparing(AbstractItem::getCategory))
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryItem> sortByPrice() {
        List<InventoryItem> items = MyFileHandler.getAllFromFile(itemsPath, InventoryItem[].class);

        return items.stream()
                .sorted(Comparator.comparing(InventoryItem::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryItem> search(String name) {
        List<InventoryItem> items = MyFileHandler.getAllFromFile(itemsPath, InventoryItem[].class);

        return items.stream()
                .filter(i -> i.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryItem> search(ItemCategory category) {
        List<InventoryItem> items = MyFileHandler.getAllFromFile(itemsPath, InventoryItem[].class);

        return items.stream()
                .filter(i -> i.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public InventoryItem updateByClient(long id, Number quantity) throws ItemNotFoundException, IOCustomException, ItemNotValidException, ItemQuantityNotEnoughException {
        List<InventoryItem> items = MyFileHandler.getAllFromFile(itemsPath, InventoryItem[].class);
        InventoryItem inventoryItem = getById(id);

        checkAvailability(inventoryItem, quantity);

        if (inventoryItem.getQuantityPerKilogram() != null) {
            inventoryItem.setQuantityPerKilogram(inventoryItem.getQuantityPerKilogram() - (double) quantity);
        } else if (inventoryItem.getQuantityPerPiece() != null) {
            inventoryItem.setQuantityPerPiece(inventoryItem.getQuantityPerPiece() - (int) quantity);
        }

        MyFileHandler.saveToFile(items, itemsPath);

        return inventoryItem;
    }
}
