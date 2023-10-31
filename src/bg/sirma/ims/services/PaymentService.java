package bg.sirma.ims.services;

import bg.sirma.ims.exception.*;
import bg.sirma.ims.model.order.Order;
import bg.sirma.ims.model.payment.PayPalAccount;
import bg.sirma.ims.model.payment.PaymentMethod;

public interface PaymentService {
    void pay(Order order, String pin) throws PermissionDeniedException, NotEnoughFundsException;
    PaymentMethod addCardPayment(String cardNumber) throws PermissionDeniedException, IOCustomException;
    PaymentMethod addPayPalPayment(PayPalAccount account) throws PermissionDeniedException, IOCustomException;
    PaymentMethod getById(long id) throws PaymentMethodNotFoundException;
}
