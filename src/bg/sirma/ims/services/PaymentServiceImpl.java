package bg.sirma.ims.services;

import bg.sirma.ims.exception.*;
import bg.sirma.ims.fileHandlers.MyFileHandler;
import bg.sirma.ims.http.*;
import bg.sirma.ims.model.item.InventoryItem;
import bg.sirma.ims.model.order.Order;
import bg.sirma.ims.model.payment.CardPayment;
import bg.sirma.ims.model.payment.PayPalAccount;
import bg.sirma.ims.model.payment.PayPalPayment;
import bg.sirma.ims.model.payment.PaymentMethod;
import bg.sirma.ims.model.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static bg.sirma.ims.constants.Constants.paymentsPath;

public class PaymentServiceImpl implements PaymentService {
    private static final OrderService orderService = new OrderServiceImpl();
    private static final MockData mockData = new MockData().mock();
    private static final Bank bank = mockData.getBank();
    private static final PayPal payPal = mockData.getPayPal();

    private User getCurrentUser() {
        return UserServiceImpl.getCurrentUser();
    }

    private boolean checkBalance(Order order, String pin) throws PermissionDeniedException, NotEnoughFundsException {
        PaymentMethod paymentMethod = order.getPayment();
        if (paymentMethod instanceof CardPayment) {
            BankAccount bankAccount = bank.getAccounts().stream()
                    .filter(a -> a.getCardNumber().equals(((CardPayment) paymentMethod).getCardNumber())
                            && a.getPin().equals(pin))
                    .findFirst()
                    .orElseThrow(() -> new PermissionDeniedException("You do not have permissions for that!!!"));

            BigDecimal totalCost = orderService.totalCost(order.getCart());
            if (bankAccount.getBalance().compareTo(totalCost) < 0) {
                throw new NotEnoughFundsException("There is not enough funds on your balance!!!");
            }

            return true;
        }

        return false;
    }

    private boolean checkBalance(Order order) throws PermissionDeniedException, NotEnoughFundsException {
        PaymentMethod paymentMethod = order.getPayment();
        if (paymentMethod instanceof PayPalPayment) {
            PayPalAccountRemote payPalAccountRemote = payPal.getAccounts().stream()
                    .filter(a -> a.getUsername().equals(((PayPalPayment) paymentMethod).getPaypalAccount().getUsername())
                            && a.getPassword().equals(((PayPalPayment) paymentMethod).getPaypalAccount().getPassword()))
                    .findFirst()
                    .orElseThrow(() -> new PermissionDeniedException("You do not have permissions for that!!!"));

            BigDecimal totalCost = orderService.totalCost(order.getCart());
            if (payPalAccountRemote.getBalance().compareTo(totalCost) < 0) {
                throw new NotEnoughFundsException("There is not enough funds on your PayPal account!!!");
            }

            return true;
        }

        return false;
    }

    private void processPay(BigDecimal cost, PaymentMethod paymentMethod) throws PermissionDeniedException {
        if (paymentMethod instanceof CardPayment) {
            String cardNumber = ((CardPayment) paymentMethod).getCardNumber();
            BankAccount bankAccount = bank.getAccounts().stream()
                    .filter(a -> a.getCardNumber().equals(cardNumber))
                    .findFirst()
                    .orElseThrow(() -> new PermissionDeniedException("You do not have permissions for that!!!"));
            bankAccount.decreaseBalance(cost);
        }

        if (paymentMethod instanceof PayPalPayment) {
            PayPalAccount paypalAccount = ((PayPalPayment) paymentMethod).getPaypalAccount();
            PayPalAccountRemote payPalAccountRemote = payPal.getAccounts().stream()
                    .filter(a -> a.getUsername().equals(paypalAccount.getUsername()))
                    .findFirst()
                    .orElseThrow(() -> new PermissionDeniedException("You do not have permissions for that!!!"));
            payPalAccountRemote.decreaseBalance(cost);
        }
    }

    @Override
    public void pay(Order order, String pin) throws PermissionDeniedException, NotEnoughFundsException {
        PaymentMethod payment = order.getPayment();
        User payer = payment.getPayer();
        User currentUser = getCurrentUser();
        if (!currentUser.getUsername().equals(payer.getUsername())) {
            throw new PermissionDeniedException("You do not have permissions for that!!!");
        }

        boolean validPay = pin != null ? checkBalance(order, pin) : checkBalance(order);
        if (validPay) {
            BigDecimal totalCost = orderService.totalCost(order.getCart());
            processPay(totalCost, payment);
        }
    }

    @Override
    public PaymentMethod addCardPayment(String cardNumber) throws PermissionDeniedException, IOCustomException {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            throw new PermissionDeniedException("You do not have permissions for that!!!");
        }

        CardPayment cardPayment = new CardPayment(currentUser, cardNumber);
        List<PaymentMethod> payments = MyFileHandler.getAllFromFile(paymentsPath, PaymentMethod[].class);
        long lastId = MyFileHandler.getLastId(payments);
        cardPayment.setId(lastId + 1);
        payments.add(cardPayment);
        MyFileHandler.saveToFile(payments, paymentsPath);

        return cardPayment;
    }

    @Override
    public PaymentMethod addPayPalPayment(PayPalAccount account) throws PermissionDeniedException, IOCustomException {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            throw new PermissionDeniedException("You do not have permissions for that!!!");
        }

        PayPalPayment payPalPayment = new PayPalPayment(currentUser, account);
        List<PaymentMethod> payments = MyFileHandler.getAllFromFile(paymentsPath, PaymentMethod[].class);
        long lastId = MyFileHandler.getLastId(payments);
        payPalPayment.setId(lastId + 1);
        payments.add(payPalPayment);
        MyFileHandler.saveToFile(payments, paymentsPath);

        return payPalPayment;
    }

    @Override
    public PaymentMethod getByTypeAndUserUsername(Class<? extends PaymentMethod> paymentMethodClass) throws PaymentMethodNotFoundException {
        String username = UserServiceImpl.getCurrentUser().getUsername();
        List<PaymentMethod> paymentMethods = MyFileHandler.getAllFromFile(paymentsPath, PaymentMethod[].class);
        return paymentMethods.stream()
                .filter(p -> p.getPayer().getUsername().equals(username) && p.getClass().equals(paymentMethodClass))
                .findFirst()
                .orElseThrow(() -> new PaymentMethodNotFoundException(String.format("Payment method not found on username (%s)", username)));
    }
}
