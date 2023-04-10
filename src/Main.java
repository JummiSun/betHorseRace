

import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final PlayerManager playerManager = new PlayerManager();
    private static final HorseManager horseManager = new HorseManager();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("---- Main Menu ----\n1) Player\n2) Admin\nSelect: ");
        int role = sc.nextInt();

        while (true) {
            if (role == 1) {
                System.out.println("----- Player Commands ----");
                System.out.print("1) Register\n2) Login\n3) Logout\n4) Current player\n5) Place bet\n6) Back\nSelect: ");
                int command = sc.nextInt();
                handlePlayerCommand(command);
            } else if (role == 2) {
                System.out.println("----- Admin Commands ----");
                System.out.print("1) Login\n2) Show horses\n3) Add horses\n4) Back\nSelect: ");
                int command = sc.nextInt();
                handleAdminCommand(command);
            }
        }
    }

    private static void handlePlayerCommand(int command) {
        if (command == PlayerCommand.REGISTER) {
            handlePlayerRegisterCommand();
        } else if (command == PlayerCommand.LOGIN) {
            handlePlayerLoginCommand();
        } else if (command == PlayerCommand.LOGOUT) {
            handlePlayerLogoutCommand();
        } else if (command == PlayerCommand.BACK) {
            mainMenu();
        } else if (command == PlayerCommand.SHOW_CURRENT_PLAYER) {
            handleShowCurrentPlayerCommand();
        } else if (command == PlayerCommand.PLACE_BET) {
            if (!playerManager.isPlayerLoggedIn()) {
                System.out.println("Player should be logged in to place bet");
            } else {
                handlePlayerPlaceBetCommand();
            }
        }
    }

    private static void handlePlayerPlaceBetCommand() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("----- Bet types ----");
            System.out.print("1)Put bet\n2) Back\nSelect: ");
            int command = sc.nextInt();
            if (command == 2) {
                mainMenu();
            } else if (command == 1) {
                handleBetHorseCommand();
            }
//            else if (command == 2) {
//                handleLoseHorseCommand();
//            }
        }
    }

    //    private static void handleLoseHorseCommand() {
//        Set<Horse> horses = horseManager.getHorses();
//        if (horses.isEmpty()) {
//            System.out.println("There are no horses");
//        } else {
//            System.out.println("Registered horses are: ");
//            for (Horse horse : horses) {
//                System.out.println(horse.getId() + ") " + horse.getName());
//            }
//
//            System.out.print("Select horse ID: ");
//            int horseId = new Scanner(System.in).nextInt();
//            Horse horse = horseManager.getHorseById(horseId);
//            if (horse == null) {
//                System.out.println("Select the correct horse ID");
//            } else {
//                Horse winner = horseManager.selectRandomLoser();
//                if (winner.getId() == horseId) {
//                    System.out.println("Congratulations! You won!");
//                } else {
//                    System.out.println("You lost! Try again");
//                }
//            }
//        }
//    }
    private static void handleBetHorseCommand() {
        Set<Horse> horses = horseManager.getHorses();
        if (horses.isEmpty()) {
            System.out.println("There are no horses");
        } else {
            System.out.println("Registered horses are: ");
            for (Horse horse : horses) {
                System.out.println(horse.getId() + ") " + horse.getName());
            }

            System.out.print("Choose the horse ID on bet: ");
            int horseId = new Scanner(System.in).nextInt();
            Horse horse = horseManager.getHorseById(horseId);
            if (horse == null) {
                System.out.println("Select the correct horse ID");
            } else {
                Horse winner = horseManager.selectRandomPlace();
                if (winner.getId() == horseId) {
                    System.out.println("Congratulations! You won!");
                } else {
                    System.out.println("You lost! Try again");
                }
            }
        }
    }

    private static void handleShowCurrentPlayerCommand() {
        System.out.println("Current player: " + playerManager.getCurrentPlayer());
    }

    private static void handlePlayerLogoutCommand() {
        playerManager.logout();
        System.out.println("Player has been successfully logged out");
    }


    private static void handlePlayerRegisterCommand() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter login: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        try {
            playerManager.register(username, password, false);
            System.out.println("Player has been successfully registered");
        } catch (IllegalArgumentException e) {
            System.out.println("Player registration failed with: " + e.getMessage());
        }
    }

    private static void handlePlayerLoginCommand() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter login: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        try {
            playerManager.login(username, password);
            System.out.println("Player has been logged in successfully");

        } catch (IllegalArgumentException e) {
            System.out.println("Player login failed with: " + e.getMessage());
        }
    }

    private static void handleAdminCommand(int command) {
        if (command == AdminCommand.ADD) {
            if (playerManager.isAdminLoggedIn()) {
                handleHorseAddCommand();
            } else {
                System.out.println("You have to log in to the system");
            }
        } else if (command == AdminCommand.SHOW) {
            handleHorseShowCommand();
        } else if (command == AdminCommand.LOGIN) {
            handleAdminLogInCommand();
        } else if (command == AdminCommand.BACK) {
            mainMenu();
        }
    }

    private static void handleAdminLogInCommand() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter admin name: ");
        String username = sc.next();
        System.out.print("Enter admin password: ");
        String password = sc.next();

        try {
            playerManager.adminLogin(username, password);
            System.out.println("Admin has been logged in successfully");

        } catch (IllegalArgumentException e) {
            System.out.println("Admin login failed with: " + e.getMessage());
        }

    }

    private static void handleHorseAddCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter horse ID: ");
        int horseId = sc.nextInt();
        System.out.print("Enter name: ");
        String horseName = sc.next();

        try {
            horseManager.addHorse(horseId, horseName);
            System.out.println("Horse has added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Horse adding failed with: " + e.getMessage());
        }
    }

    private static void handleHorseShowCommand() {
        Set<Horse> horses = horseManager.getHorses();
        if (horses.isEmpty()) {
            System.out.println("There are no registered horses");
        } else {
            System.out.println("Registered horses are:");
            for (Horse horse : horses) {
                System.out.println(horse.getId() + ") " + horse.getName());
            }
        }
    }
}
