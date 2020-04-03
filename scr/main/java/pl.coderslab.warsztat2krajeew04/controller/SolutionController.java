package pl.coderslab.warsztat2krajeew04.controller;

import pl.coderslab.warsztat2krajeew04.dao.ExerciseDao;
import pl.coderslab.warsztat2krajeew04.dao.SolutionDao;
import pl.coderslab.warsztat2krajeew04.dao.UserDao;
import pl.coderslab.warsztat2krajeew04.model.Exercise;
import pl.coderslab.warsztat2krajeew04.model.Solution;
import pl.coderslab.warsztat2krajeew04.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class SolutionController {


    public SolutionController() {
        solutionMenu();
    }

    public static void main(String[] args) {
        solutionMenu();
    }

    /**
     * Method with solution menu
     */
    private static void solutionMenu() {
        System.out.println("Welcome to user administration panel");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Select option and hit enter. Options:");
            System.out.println("1 - Add");
            System.out.println("2 - View");
            System.out.println("0 - Quit");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            } else if (input.equals("1")) {
                add();
            } else if (input.equals("2")) {
                view();
            } else {
                System.out.println("Provide a proper menu option!");
            }
        }
        System.out.println("Program has ended!");
    }


    /**
     * Method view date of solution
     */
    private static void view() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user id: ");
        int userId = Integer.parseInt(scanner.nextLine());
        List<Solution> solutionList = new SolutionDao().findAllByUserId(userId);
        System.out.println(String.format("|%3s|%20s|", "ID", "Description"));
        for (Solution solution : solutionList){
            System.out.println(String.format("|%3s|%20s|", solution.getId(), solution.getDescription()));
        }
    }

    /**
     * Method creating new solution
     */
    private static void add() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chose user by id");
        System.out.println(String.format("|%3s|%7s|", "ID", "User name"));
        List<User> usersList = new UserDao().findAll();
        for (User user : usersList) {
            System.out.println(String.format("|%3d|%7s", user.getId(), user.getUsername()));
        }
        int userId = Integer.parseInt(scanner.nextLine());

        System.out.println("Chose solution by id");
        List<Solution> solutionList = new SolutionDao().findAll();
        System.out.println(String.format("|%3s|%20s|", "ID", "Description"));
        for (Solution solution : solutionList){
            System.out.println(String.format("|%3s|%20s|", solution.getId(), solution.getDescription()));
        }
        int solutuinId = Integer.parseInt(scanner.nextLine());
        Solution solution = new Solution();
        solution.setId(solutuinId);
        solution.setCreated(LocalDateTime.now());
        solution.setUserId(userId);
    }
}
