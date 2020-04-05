package pl.coderslab.warsztat2krajeew04.controller;

import pl.coderslab.warsztat2krajeew04.dao.SolutionDao;
import pl.coderslab.warsztat2krajeew04.model.Solution;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AddRatingController {
    public static void main(String[] args) {
        menu();
    }

    /**
     * Method with menu
     */
    private static void menu() {
        System.out.println("Wpisz id usera for adding point or comment");
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


    private static void view(int userId) {
        List<Solution> solutionList = new SolutionDao().findAllByUserId(userId);
        System.out.println(String.format("|%3s|%20s|", "ID", "Description"));
        for (Solution solution : solutionList) {
            System.out.println(String.format("|%3s|%20s|", solution.getId(), solution.getDescription()));
        }
    }

    private static void add(int userId) {
        Scanner scanner = new Scanner(System.in);
        List<Solution> solutionList = new SolutionDao().findAllByUserId(userId);
        System.out.println(String.format("|%3s|%20s|", "ID", "Description"));
        for (Solution solution : solutionList) {
            if (solution.getDescription() != null) {
                System.out.println(String.format("|%3s|%20s|", solution.getId(), solution.getDescription()));
            }
        }
        int solutionId;
        int i = 0;
        System.out.println("Chose id of exercise or type 0 go to menu");
        do {
            solutionId = Integer.parseInt(scanner.nextLine());
            for (Solution solution : solutionList) {
                if (solutionId == solution.getId() && solution.getDescription()==null) {
                    System.out.println("Write point");
                    int point = Integer.parseInt(scanner.nextLine());
                    System.out.println("Write comment");
                    String commentar = scanner.nextLine();
                    Solution solution1 = new Solution();
                    solution1.setId(solutionId);
                    solution1.setPoint(point);
                    solution1.setCommentar(commentar);
                    new SolutionDao().updateRating(solution1);
                    i++;
                }
            }
            if (i == 0) {
                System.out.println("Didn't found solution with that id or type 0 go to menu ");
            }
        } while (i != 1 && solutionId!=0 );
    }


}
