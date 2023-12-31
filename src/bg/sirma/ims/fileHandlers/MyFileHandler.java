package bg.sirma.ims.fileHandlers;

import bg.sirma.ims.exception.IOCustomException;
import bg.sirma.ims.model.item.ElectronicItem;
import bg.sirma.ims.model.item.FragileItem;
import bg.sirma.ims.model.item.GroceryItem;
import bg.sirma.ims.model.item.InventoryItem;
import bg.sirma.ims.model.order.Order;
import bg.sirma.ims.model.payment.CardPayment;
import bg.sirma.ims.model.payment.PayPalPayment;
import bg.sirma.ims.model.payment.PaymentMethod;
import bg.sirma.ims.model.user.User;
import bg.sirma.ims.serializer.InventoryItemDeserializer;
import bg.sirma.ims.serializer.LocalDateDeserializer;
import bg.sirma.ims.serializer.PaymentMethodDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static bg.sirma.ims.constants.Constants.basePath;

public class MyFileHandler {
    private static final InventoryItemDeserializer deserializer = new InventoryItemDeserializer("type")
            .registerItemType("InventoryItem", InventoryItem.class)
            .registerItemType("GroceryItem", GroceryItem.class)
            .registerItemType("ElectronicItem", ElectronicItem.class)
            .registerItemType("FragileItem", FragileItem.class);
    private static final PaymentMethodDeserializer paymentDeserializer = new PaymentMethodDeserializer("type")
            .registerMethodType("CardPayment", CardPayment.class)
            .registerMethodType("PayPalPayment", PayPalPayment.class);
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .registerTypeAdapter(InventoryItem.class, deserializer)
            .registerTypeAdapter(PaymentMethod.class, paymentDeserializer)
            .setPrettyPrinting()
            .create();

    public static <E> List<E> getAllFromFile(String path, Class<E[]> clazz) {
        E[] objects = (E[]) new Object[0];
        try (Reader reader = Files.newBufferedReader(Path.of(basePath + path))) {
            objects = gson.fromJson(reader, clazz);
        } catch (IOException ignored) {

        }

        return Arrays.stream(objects).collect(Collectors.toList());
    }

    public static <E> void saveToFile(E object, String path) throws IOCustomException {
        try (Writer writer = Files.newBufferedWriter(Path.of(basePath + path))) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            throw new IOCustomException("Something went wrong. Try again");
        }
    }

    public static <E> void saveToFile(List<E> objects, String path) throws IOCustomException {
        try (Writer writer = Files.newBufferedWriter(Path.of(basePath + path))) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new IOCustomException("Something went wrong. Try again");
        }
    }

    public static <E> long getLastId(List<E> list) {
        return list.stream().map(e -> {
            if (e instanceof User) {
                return ((User) e).getId();
            } else if (e instanceof InventoryItem) {
                return ((InventoryItem) e).getId();
            } else if (e instanceof Order) {
                return ((Order) e).getId();
            } else if (e instanceof PaymentMethod) {
                return ((PaymentMethod) e).getId();
            }
            return null;
                })
                .mapToLong(aLong -> aLong != null ? aLong : 0)
                .max()
                .orElse(0);
    }
}
