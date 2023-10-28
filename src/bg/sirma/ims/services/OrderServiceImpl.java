package bg.sirma.ims.services;

import bg.sirma.ims.exception.*;
import bg.sirma.ims.model.item.InventoryItem;
import bg.sirma.ims.model.order.Order;
import bg.sirma.ims.model.payment.*;
import bg.sirma.ims.model.user.User;

import java.math.BigDecimal;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private static final ItemService itemService = new ItemServiceImpl();

    @Override
    public BigDecimal totalCost(Order order) throws PermissionDeniedException {
        User currentUser = UserServiceImpl.getCurrentUser();
        if (currentUser == null) {
            throw new PermissionDeniedException("You do not have permissions for that!!!");
        }

        return order.getCart().getShoppingMap().entrySet().stream()
                .map(e -> e.getKey().getPrice().multiply((BigDecimal) e.getValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Order order(Order order) throws PermissionDeniedException, IOCustomException, ItemQuantityNotEnoughException, ItemNotValidException, ItemNotFoundException {
        User currentUser = UserServiceImpl.getCurrentUser();
        if (currentUser == null) {
            throw new PermissionDeniedException("You do not have permissions for that!!!");
        }

        PaymentMethod payment = order.getPayment();
        User payer = payment.getPayer();
        if (!currentUser.getUsername().equals(payer.getUsername())) {
            throw new PermissionDeniedException("You do not have permissions for that!!!");
        }

        for (Map.Entry<InventoryItem, Number> e : order.getCart().getShoppingMap().entrySet()) {
            itemService.updateByClient(e.getKey().getId(), e.getValue());
        }

        return order;
    }
}


