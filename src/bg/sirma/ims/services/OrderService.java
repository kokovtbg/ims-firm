package bg.sirma.ims.services;

import bg.sirma.ims.exception.*;
import bg.sirma.ims.model.order.Order;

import java.math.BigDecimal;

public interface OrderService {
    BigDecimal totalCost(Order order) throws PermissionDeniedException;
    Order order(Order order, String pin) throws PermissionDeniedException, IOCustomException, ItemQuantityNotEnoughException, ItemNotValidException, ItemNotFoundException, NotEnoughFundsException;
}
