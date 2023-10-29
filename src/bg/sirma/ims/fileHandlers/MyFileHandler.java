package bg.sirma.ims.fileHandlers;

import bg.sirma.ims.exception.IOCustomException;
import bg.sirma.ims.model.item.InventoryItem;
import bg.sirma.ims.model.order.Order;
import bg.sirma.ims.model.payment.PaymentMethod;
import bg.sirma.ims.model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static bg.sirma.ims.constants.Constants.basePath;

public class MyFileHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <E> List<E> getAllFromFile(String path) {
        E[] objects = (E[]) new Object[0];
        try (Reader reader = Files.newBufferedReader(Path.of(basePath + path))) {
            objects = (E[]) gson.fromJson(reader, Object[].class);
        } catch (IOException ignored) {

        }

        return Arrays.stream(objects).toList();
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
