package web.view;

import java.util.Scanner;

public class Input {
    private static final int BOARD_START = 1;
    private static final int BOARD_END = 8;
    private static final Scanner scanner = new Scanner(System.in);
    public static String[] userInputArray;

    // start, end, move a1 b2, status
    public static void getUserInput() {
        String userInput = scanner.nextLine();
        userInputArray = userInput.split(" ");
    }

}
