package bg.sirma.ims.exception;

public class UserExistException extends Exception {
    public UserExistException(String format) {
        super(format);
    }
}
