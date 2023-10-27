package bg.sirma.ims.services;

import bg.sirma.ims.exception.IOCustomException;
import bg.sirma.ims.exception.UserCredentialsNotValidException;
import bg.sirma.ims.exception.UserExistException;
import bg.sirma.ims.model.user.User;

public interface UserService {
    void register(String username, String password) throws UserExistException, IOCustomException;
    User login(String username, String password) throws UserCredentialsNotValidException;
    void logout();
}
