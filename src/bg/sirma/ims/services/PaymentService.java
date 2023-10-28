package bg.sirma.ims.services;

import bg.sirma.ims.model.payment.PaymentMethod;

public interface PaymentService {
    PaymentMethod pay(PaymentMethod payment);
}
