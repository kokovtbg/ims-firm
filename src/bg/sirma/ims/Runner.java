package bg.sirma.ims;

import bg.sirma.ims.exception.*;
import bg.sirma.ims.model.item.*;
import bg.sirma.ims.services.ItemService;
import bg.sirma.ims.services.ItemServiceImpl;
import bg.sirma.ims.services.UserService;
import bg.sirma.ims.services.UserServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Runner {
    private static final UserService userService = new UserServiceImpl();
    private static final ItemService itemService = new ItemServiceImpl();

    public static void run() {
        System.out.println("Welcome to Inventory Management System!!!");
        showMenu();

        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        while (!line.equals("0")) {
            showMenu();

            try {
                int command = Integer.parseInt(line);
                switch (command) {
                    case 1 -> {
                        printCommandEnum(CommandEnum.REGISTER);
                        String[] commandData = scanner.nextLine().split("\\s+");
                        String username = commandData[0];
                        String password = commandData[1];
                        userService.register(username, password);
                        printMessageToUser(String.format("User with username: (%s) and password (%s)%n REGISTERED!!!", username, password));
                    }
                    case 2 -> {
                        printCommandEnum(CommandEnum.LOGIN);
                        String[] commandData = scanner.nextLine().split("\\s+");
                        String username = commandData[0];
                        String password = commandData[1];
                        userService.login(username, password);
                        printMessageToUser(String.format("User with username: (%s)%n LOGGED IN!!!", username));
                    }
                    case 3 -> {
                        userService.logout();
                        printMessageToUser("LOGGED OUT!!!");
                    }
                    case 4 -> {
                        printCommandEnum(CommandEnum.ITEM_ADD_KILOGRAM);
                        String[] commandData = scanner.nextLine().split("\\s*\\|\\s*");
                        String name = commandData[0];
                        String manufacturer = commandData[1];
                        String description = commandData[2];
                        ItemCategory itemCategory = ItemCategory.valueOf(commandData[3]);
                        double quantity = Double.parseDouble(commandData[4]);
                        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(commandData[5]));
                        InventoryItem item;
                        if (commandData.length > 6) {
                            LocalDate expirationDate = LocalDate.parse(commandData[6]);
                            item = new GroceryItem(name, manufacturer, description, itemCategory, quantity, price, expirationDate);
                        } else {
                            item = new InventoryItem(name, manufacturer, description, itemCategory, quantity, price);
                        }
                        printMessageToUser(itemService.add(item));
                    }
                    case 5 -> {
                        printCommandEnum(CommandEnum.ITEM_ADD_PIECE);
                        String[] commandData = scanner.nextLine().split("\\s*\\|\\s*");
                        String name = commandData[0];
                        String manufacturer = commandData[1];
                        String description = commandData[2];
                        ItemCategory itemCategory = ItemCategory.valueOf(commandData[3]);
                        int quantity = Integer.parseInt(commandData[4]);
                        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(commandData[5]));
                        InventoryItem item;
                        if (commandData.length == 6) {
                            item = new InventoryItem(name, manufacturer, description, itemCategory, quantity, price);
                        } else {
                            String optionalString = commandData[6];
                            switch (optionalString) {
                                case "grocery" -> {
                                    LocalDate expirationDate = LocalDate.parse(commandData[7]);
                                    item = new GroceryItem(name, manufacturer, description, itemCategory, quantity, price, expirationDate);
                                }
                                case "fragile" -> {
                                    double weight = Double.parseDouble(commandData[7]);
                                    item = new FragileItem(name, manufacturer, description, itemCategory, quantity, price, weight);
                                }
                                case "electronic" -> {
                                    int warranty = Integer.parseInt(commandData[7]);
                                    item = new ElectronicItem(name, manufacturer, description, itemCategory, quantity, price, warranty);
                                }
                                default -> throw new ItemNotValidException("No such operation");
                            }
                        }
                        printMessageToUser(itemService.add(item));
                    }
                    case 6 -> {

                    }
                }
            } catch (NumberFormatException e) {
                printMessageToUser("Must enter number!!!");
            } catch (IOCustomException | UserExistException | UserCredentialsNotValidException |
                     PermissionDeniedException | ItemNotValidException e) {
                printMessageToUser(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                printMessageToUser("Must enter more parameters");
            } catch (IllegalArgumentException e) {
                printMessageToUser("Must enter valid constant");
            } catch (DateTimeParseException e) {
                printMessageToUser("Must be valid date of type (yyyy-MM-dd)");
            }

            line = scanner.nextLine();
        }

    }

    private static void printCommandEnum(CommandEnum commandEnum) {
        System.out.println(commandEnum);
        System.out.println(commandEnum.getValue());
    }

    private static <E> void printMessageToUser(E message) {
        printGenericThreeTimes();
        System.out.println(message.toString());
        printGenericThreeTimes();
    }

    private static void printGenericThreeTimes() {
        System.out.println("<><><>");
    }

    private static void showMenu() {
        System.out.println("-------------");
        Arrays.stream(CommandEnum.values())
                .forEach(e -> System.out.printf("%d.%s->%s%n", e.ordinal(), e, e.getValue()));
        System.out.println("-------------");
    }
}
