package bg.sirma.ims.serializer;

import bg.sirma.ims.model.item.InventoryItem;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class InventoryItemDeserializer implements JsonDeserializer<InventoryItem> {
    private String inventoryTypeElementName;
    private Map<String, Class<? extends InventoryItem>> inventoryTypeRegistry;
    private Gson gson;

    public InventoryItemDeserializer(String inventoryTypeElementName) {
        this.inventoryTypeElementName = inventoryTypeElementName;
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).create();
        this.inventoryTypeRegistry = new HashMap<>();
    }

    public InventoryItemDeserializer registerItemType(String itemTypeName, Class<? extends InventoryItem> itemType) {
        inventoryTypeRegistry.put(itemTypeName, itemType);
        return this;
    }

    @Override
    public InventoryItem deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject inventoryItemObject = jsonElement.getAsJsonObject();
        JsonElement inventoryTypeElement = inventoryItemObject.get(inventoryTypeElementName);
        Class<? extends InventoryItem> inventoryType = inventoryTypeRegistry.get(inventoryTypeElement.getAsString());
        return gson.fromJson(inventoryItemObject, inventoryType);
    }

}
