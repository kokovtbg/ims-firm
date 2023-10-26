package bg.sirma.ims.fileHandlers;

import bg.sirma.ims.exception.IOCustomException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {
    private static final String basePath = "src/bg/sirma/ims/db/";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <E>E[] getAllFromFile(String path) {
        E[] objects = (E[]) new Object[0];
        try (Reader reader = Files.newBufferedReader(Path.of(basePath + path))) {
            objects = (E[]) gson.fromJson(reader, Object[].class);
        } catch (IOException ignored) {

        }

        return objects;
    }

    public static <E> void saveToFile(E object, String path) throws IOCustomException {
        try (Writer writer = Files.newBufferedWriter(Path.of(basePath + path))) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            throw new IOCustomException("Something went wrong. Try again");
        }
    }

    public static <E> void saveToFile(E[] objects, String path) throws IOCustomException {
        try (Writer writer = Files.newBufferedWriter(Path.of(basePath + path))) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new IOCustomException("Something went wrong. Try again");
        }
    }
}
