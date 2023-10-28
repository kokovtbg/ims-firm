package bg.sirma.ims.services;

import bg.sirma.ims.model.order.Order;

import java.math.BigDecimal;

public interface OrderService {
    BigDecimal totalCost();
    Order order(Order order);
}
