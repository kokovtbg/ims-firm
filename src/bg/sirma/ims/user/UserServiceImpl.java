package bg.sirma.ims.user;

import bg.sirma.ims.exception.IOCustomException;
import bg.sirma.ims.exception.UserCredentialsNotValidException;
import bg.sirma.ims.exception.UserExistException;
import bg.sirma.ims.fileHandlers.FileHandler;

import java.util.Arrays;

public class UserServiceImpl implements UserService {
    private static User currentUser = null;
    @Override
    public void register(String username, String password) throws UserExistException, IOCustomException {
        User[] allUsers = FileHandler.getAllFromFile(usersPath);
        Arrays.stream(allUsers)
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UserExistException(String
                        .format("User with username (%s) already exist", username)));
        User user = new User(username, password);
        FileHandler.saveToFile(user, usersPath);
    }

    @Override
    public User login(String username, String password) throws UserCredentialsNotValidException {
        User[] users = FileHandler.getAllFromFile(usersPath);
        User user = Arrays.stream(users)
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new UserCredentialsNotValidException("User credentials not valid!!!"));
        currentUser = user;
        return user;
    }

    @Override
    public void logout() {
        currentUser = null;
    }
}
