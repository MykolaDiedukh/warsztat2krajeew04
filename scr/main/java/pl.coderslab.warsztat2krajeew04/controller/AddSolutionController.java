package pl.coderslab.warsztat2krajeew04.controller;

import pl.coderslab.warsztat2krajeew04.dao.SolutionDao;
import pl.coderslab.warsztat2krajeew04.model.Solution;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AddSolutionController {
    public static void main(String[] args) {
        menu();
    }

    /**
     * Method with menu
     */
    private static void menu() {
        System.out.println("Wpisz id");
        Scanner scanner = new Scanner(System.in);

        int userdId = Integer.parseInt(scanner.nextLine());

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
                add(userdId);
            } else if (input.equals("2")) {
                view(userdId);
            } else {
                System.out.println("Provide a proper menu option!");
            }
        }
    }

    /**
     * Adding answer for exercise for current user
     *
     * @param userId
     */
    private static void add(int userId) {
        Scanner scanner = new Scanner(System.in);
        List<Solution> solutionList = new SolutionDao().findAllByUserId(userId);
        System.out.println(String.format("|%3s|%20s|", "ID", "Description"));
        for (Solution solution : solutionList) {
            if (solution.getDescription().equals("")) {
                System.out.println(String.format("|%3s|%20s)" + solution.getId() + solution.getDescription()));
            }
        }
        System.out.println("Chose id of exercise");
        int solutionId = Integer.parseInt(scanner.nextLine());
        while (true) {
            for (Solution solution : solutionList) {
                if (solutionId == solution.getId() && solution.getDescription().equals("")) {
                    System.out.println("Write answer");
                    String answer = scanner.nextLine();
                    Solution solution1 = new Solution();
                    solution1.setDescription(answer);
                    solution1.setUpdated(LocalDateTime.now());
                    new SolutionDao().update(solution1);
                }
            }
        }
    }

    /**
     * View all answer on exercises for current user
     *
     * @param userId
     */
    private static void view(int userId) {
        List<Solution> solutionList = new SolutionDao().findAllByUserId(userId);
        System.out.println(String.format("|%3s|%20s|", "ID", "Description"));
        for (Solution solution : solutionList) {
            System.out.println(String.format("|%3s|%20s)" + solution.getId() + solution.getDescription()));
        }
    }
}
