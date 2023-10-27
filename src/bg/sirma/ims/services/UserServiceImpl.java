package bg.sirma.ims.services;

import bg.sirma.ims.exception.IOCustomException;
import bg.sirma.ims.exception.UserCredentialsNotValidException;
import bg.sirma.ims.exception.UserExistException;
import bg.sirma.ims.fileHandlers.MyFileHandler;
import bg.sirma.ims.model.user.RoleEnum;
import bg.sirma.ims.model.user.User;

import java.util.List;

import static bg.sirma.ims.constants.Constants.usersPath;

public class UserServiceImpl implements UserService {
    private static User currentUser = null;

    public static User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void register(String username, String password) throws UserExistException, IOCustomException {
        List<User> allUsers = MyFileHandler.getAllFromFile(usersPath);
        User user = allUsers.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (user != null) {
            throw new UserExistException(String.format("User with username (%s) already exist", username));
        }
        long lastId = MyFileHandler.getLastId(allUsers);
        user = new User(lastId + 1, username, password);
        if (lastId == 0) {
            user.addRole(RoleEnum.ADMIN);
        }
        allUsers.add(user);
        MyFileHandler.saveToFile(allUsers, usersPath);
    }

    @Override
    public User login(String username, String password) throws UserCredentialsNotValidException {
        List<User> users = MyFileHandler.getAllFromFile(usersPath);
        User user = users.stream()
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
