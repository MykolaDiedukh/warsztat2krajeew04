package pl.coderslab.warsztat2krajeew04.controller;

import pl.coderslab.warsztat2krajeew04.dao.ExerciseDao;
import pl.coderslab.warsztat2krajeew04.model.Exercise;

import java.util.List;
import java.util.Scanner;

public class ExercisesController {


    public ExercisesController() {
        exerciseMenu();
    }

    public static void main(String[] args) {
        exerciseMenu();
    }

    /**
     * Method with exercise menu
     */
    private static void exerciseMenu() {
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
                add();
            } else if (input.equals("2")) {
                edit();
            } else if (input.equals("3")) {
                delete();
            } else {
                System.out.println("Provide a proper menu option!");
            }
        }
        System.out.println("Program has ended!");
    }

    /**
     * Method delete exercise by id
     */
    private static void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose exercise by id to delete");
        System.out.println(String.format("|%3s|%7s|", "ID", "exercise"));
        List<Exercise> exerciseList = new ExerciseDao().findAll();
        for (Exercise exercise : exerciseList) {
            System.out.println(String.format("|%3d|%7s", exercise.getId(), exercise.getTitle()));
        }
        String exerciseId = scanner.nextLine();
        new ExerciseDao().deleteById(Integer.parseInt(exerciseId));
        System.out.println("Exercise deleted");
    }

    /**
     * Method edit date of exercise by id
     */
    private static void edit() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chose exercise by id to edit");
        System.out.println(String.format("|%3s|%7s|", "ID", "User Name"));
        List<Exercise> exerciseList = new ExerciseDao().findAll();
        for (Exercise exercise : exerciseList) {
            System.out.println(String.format("|%3d|%7s", exercise.getId(), exercise.getTitle()));
        }
        String exerciseId = scanner.nextLine();
        System.out.println("Enter new title");
        final String title = scanner.nextLine();
        System.out.println("Enter new description");
        final String description = scanner.nextLine();

        new ExerciseDao().update(new Exercise(Integer.parseInt(exerciseId), title, description));
        System.out.println("Exercise changed");
    }

    /**
     * Method creating new exercise
     */
    private static void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title");
        final String title = scanner.nextLine();
        System.out.println("Enter description");
        final String description = scanner.nextLine();

        Exercise exercise = new Exercise();
        exercise.setTitle(title);
        exercise.setDescription(description);
        new ExerciseDao().create(exercise);
        System.out.println("Exercise saved");
    }
}
