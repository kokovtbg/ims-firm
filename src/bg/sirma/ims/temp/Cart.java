package bg.sirma.ims.temp;

import bg.sirma.ims.model.item.AbstractItem;
import bg.sirma.ims.model.item.InventoryItem;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    private final Map<InventoryItem, Number> shoppingMap;

    public Cart() {
        this.shoppingMap = new LinkedHashMap<>();
    }

    public Map<InventoryItem, Number> getShoppingMap() {
        return shoppingMap;
    }

    public void addToCart(InventoryItem item, Number quantity) {
        if (!shoppingMap.containsKey(item)) {
            if (quantity instanceof Integer) {
                shoppingMap.put(item, 0);
            } else {
                shoppingMap.put(item, 0.0);
            }
        }

        if (quantity instanceof Integer) {
            shoppingMap.put(item, (int) shoppingMap.get(item) + (int) quantity);
        } else {
            shoppingMap.put(item, (double) shoppingMap.get(item) + (double) quantity);
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "shoppingMap=" + shoppingMap.keySet().stream()
                .map(AbstractItem::toString).collect(Collectors.joining(",")) +
                '}';
    }
}
