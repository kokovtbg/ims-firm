package bg.sirma.ims.exception;

public class PaymentMethodNotFoundException extends Exception {
    public PaymentMethodNotFoundException(String format) {
        super(format);
    }
}
