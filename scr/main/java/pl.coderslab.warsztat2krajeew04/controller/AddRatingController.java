package pl.coderslab.warsztat2krajeew04.controller;

import java.util.Scanner;

public class AddRatingController {
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

    private static void view(int userdId) {

    }

    private static void add(int userdId) {
    }


}
