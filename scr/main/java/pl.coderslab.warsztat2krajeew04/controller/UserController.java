package pl.coderslab.warsztat2krajeew04.controller;

import pl.coderslab.warsztat2krajeew04.dao.UserDao;
import pl.coderslab.warsztat2krajeew04.dao.UserGroupDao;
import pl.coderslab.warsztat2krajeew04.model.User;
import pl.coderslab.warsztat2krajeew04.model.UserGroup;

import java.util.List;
import java.util.Scanner;

public class UserController {


    public UserController() {
        userMenu();
    }

    public static void main(String[] args) {
        userMenu();
    }

    /**
     * Method with user menu
     */
    private static void userMenu() {
        System.out.println("Welcome to user administration panel");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Select option and hit enter. Options:");
            System.out.println("1 - Add");
            System.out.println("2 - Edit");
            System.out.println("3 - Delete");
            System.out.println("0 - Quit");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            } else if (input.equals("1")) {
                addUser();
            } else if (input.equals("2")) {
                editUser();
            } else if (input.equals("3")) {
                deleteUser();
            } else {
                System.out.println("Provide a proper menu option!");
            }
        }
        System.out.println("Program has ended!");
    }

    /**
     * Method delete user by id
     */
    private static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose user by id to delete");
        System.out.println(String.format("|%3s|%7s|", "ID", "User name"));
        List<User> usersList = new UserDao().findAll();
        for (User user : usersList) {
            System.out.println(String.format("|%3d|%7s", user.getId(), user.getUsername()));
        }
        String userId = scanner.nextLine();
        new UserDao().deleteById(Integer.parseInt(userId));
        System.out.println("User deleted");
    }

    /**
     * Method edit date of user by id
     */
    private static void editUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chose user by id to edit");
        System.out.println(String.format("|%3s|%7s|", "ID", "User Name"));
        List<User> usersList = new UserDao().findAll();
        for (User user : usersList) {
            System.out.println(String.format("|%3d|%7s", user.getId(), user.getUsername()));
        }
        String userId = scanner.nextLine();

        System.out.println("Enter new username");
        final String username = scanner.nextLine();
        System.out.println("Enter new email");
        final String email = scanner.nextLine();
        System.out.println("Enter new password");
        final String password = scanner.nextLine();
        System.out.println("Chose a group from list by id");
        System.out.println(String.format("|%3s|%6s|", "ID", "Group"));
        List<UserGroup> usersGroupList = new UserGroupDao().findAll();
        for (UserGroup group : usersGroupList) {
            System.out.println(String.format("|%3d|%6s", group.getId(), group.getName()));
        }
        final String group = scanner.nextLine();
        new UserDao().update(new User(Integer.parseInt(userId), username, email, password, Integer.parseInt(group)));
        System.out.println("User changed");
    }

    /**
     * Method creating new user
     */
    private static void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        final String username = scanner.nextLine();
        System.out.println("Enter email");
        final String email = scanner.nextLine();
        System.out.println("Enter password");
        final String password = scanner.nextLine();
        System.out.println("Chose a group from list by id");
        System.out.println(String.format("|%3s|%6s|", "ID", "Group"));
        List<UserGroup> usersGroupList = new UserGroupDao().findAll();
        for (UserGroup group : usersGroupList) {
            System.out.println(String.format("|%3d|%6s", group.getId(), group.getName()));
        }
        final String group = scanner.nextLine();
        new UserDao().create(new User(username, email, password, Integer.parseInt(group)));
        System.out.println("User saved");
    }
}
