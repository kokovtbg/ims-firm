package bg.sirma.ims;

import bg.sirma.ims.exception.IOCustomException;
import bg.sirma.ims.exception.UserCredentialsNotValidException;
import bg.sirma.ims.exception.UserExistException;
import bg.sirma.ims.services.UserService;
import bg.sirma.ims.services.UserServiceImpl;

import java.util.Arrays;
import java.util.Scanner;

public class Runner {
    private static final UserService userService = new UserServiceImpl();

    public static void run() {
        System.out.println("Welcome to Inventory Management System!!!");
        showMenu();

        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        while (!line.equals("0")) {
            try {
                int command = Integer.parseInt(line);
                switch (command) {
                    case 1 -> {
                        System.out.println(CommandEnum.REGISTER);
                        System.out.println(CommandEnum.REGISTER.getValue());
                        String[] commandData = scanner.nextLine().split("\\s+");
                        String username = commandData[0];
                        String password = commandData[1];
                        userService.register(username, password);
                        printMessageToUser(String.format("User with username: (%s) and password (%s)%n REGISTERED!!!", username, password));
                    }
                    case 2 -> {
                        System.out.println(CommandEnum.LOGIN);
                        System.out.println(CommandEnum.LOGIN.getValue());
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

                    }
                }
            } catch (NumberFormatException e) {
                printGenericThreeTimes();
                System.out.println("Must enter number!!!");
                printGenericThreeTimes();
            } catch (IOCustomException | UserExistException | UserCredentialsNotValidException e) {
                printGenericThreeTimes();
                System.out.println(e.getMessage());
                printGenericThreeTimes();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Must enter more parameters");
            }

            showMenu();

            line = scanner.nextLine();
        }

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
        Arrays.stream(CommandEnum.values())
                .forEach(e -> System.out.printf("%d.%s->%s%n", e.ordinal(), e, e.getValue()));
    }
}
