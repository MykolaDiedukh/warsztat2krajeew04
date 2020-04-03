package pl.coderslab.warsztat2krajeew04.controller;

import pl.coderslab.warsztat2krajeew04.dao.UserDao;
import pl.coderslab.warsztat2krajeew04.dao.UserGroupDao;
import pl.coderslab.warsztat2krajeew04.model.User;
import pl.coderslab.warsztat2krajeew04.model.UserGroup;

import java.util.List;
import java.util.Scanner;

public class GroupController {

    public static void main(String[] args) {
        groupMenu();
    }

    private static void groupMenu() {
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
                addGroup();
            } else if (input.equals("2")) {
                editGroup();
            } else if (input.equals("3")) {
                deleteGroup();
            } else {
                System.out.println("Provide a proper menu option!");
            }
        }
        System.out.println("Program has ended!");
    }

    private static void deleteGroup() {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Chose group by id to delete");
        System.out.println(String.format("|%3s|%6s|", "ID", "Group"));
        List<UserGroup> usersGroupList = new UserGroupDao().findAll();
        for (UserGroup group : usersGroupList) {
            System.out.println(String.format("|%3d|%6s", group.getId(), group.getName()));
        }
        final String groupId = scanner.nextLine();
        new UserGroupDao().deleteById(Integer.parseInt(groupId));
        System.out.println("Group deleted");
    }

    private static void editGroup() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chose group by id to edit");
        System.out.println(String.format("|%3s|%6s|", "ID", "Group name"));
        List<UserGroup> usersGroupList = new UserGroupDao().findAll();
        for (UserGroup group : usersGroupList) {
            System.out.println(String.format("|%3d|%6s", group.getId(), group.getName()));
        }
        final String groupId = scanner.nextLine();

        System.out.println("Enter new name for group");
        final String groupName = scanner.nextLine();
        new UserGroupDao().update(new UserGroup(Integer.parseInt(groupId), groupName));

        System.out.println("Group changed");
    }

    private static void addGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of group");
        final String groupName = scanner.nextLine();
        new UserGroupDao().create(new UserGroup(groupName));
        System.out.println("Group saved");
    }
}
