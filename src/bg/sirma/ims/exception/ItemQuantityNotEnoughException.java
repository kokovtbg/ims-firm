package bg.sirma.ims.exception;

public class ItemQuantityNotEnoughException extends Exception {
    public ItemQuantityNotEnoughException(String format) {
        super(format);
    }
}
