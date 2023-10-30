package bg.sirma.ims.services;

import bg.sirma.ims.exception.*;
import bg.sirma.ims.fileHandlers.MyFileHandler;
import bg.sirma.ims.model.item.InventoryItem;
import bg.sirma.ims.model.order.Order;
import bg.sirma.ims.model.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static bg.sirma.ims.constants.Constants.ordersPath;

public class OrderServiceImpl implements OrderService {
    private static final PaymentService paymentService = new PaymentServiceImpl();
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
    public Order order(Order order, String pin) throws PermissionDeniedException, IOCustomException, ItemQuantityNotEnoughException, ItemNotValidException, ItemNotFoundException, NotEnoughFundsException {
        User currentUser = UserServiceImpl.getCurrentUser();
        if (currentUser == null) {
            throw new PermissionDeniedException("You do not have permissions for that!!!");
        }

        paymentService.pay(order, pin);

        for (Map.Entry<InventoryItem, Number> e : order.getCart().getShoppingMap().entrySet()) {
            itemService.updateByClient(e.getKey().getId(), e.getValue());
        }

        saveOrder(order);

        return order;
    }

    private static void saveOrder(Order order) throws IOCustomException {
        List<Order> orders = MyFileHandler.getAllFromFile(ordersPath, Order[].class);
        long lastId = MyFileHandler.getLastId(orders);
        order.setId(lastId + 1);
        orders.add(order);
        MyFileHandler.saveToFile(orders, ordersPath);
    }
}


