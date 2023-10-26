package bg.sirma.ims.user;

import bg.sirma.ims.exception.IOCustomException;
import bg.sirma.ims.exception.UserCredentialsNotValidException;
import bg.sirma.ims.exception.UserExistException;
import bg.sirma.ims.item.InventoryItem;

public interface UserService {
    String usersPath = "users.json";
    void register(String username, String password) throws UserExistException, IOCustomException;
    User login(String username, String password) throws UserCredentialsNotValidException;
    void logout();
}
