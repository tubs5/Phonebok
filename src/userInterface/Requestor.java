package userInterface;

import java.util.Scanner;

/**
 * @author Tobias Heidlund
 */
public class Requestor {

    private final Scanner scanner = new Scanner(System.in);

    public int getInt(String request) {
        System.out.println(request);
        boolean hasNumber = false;
        int response = 0;
        while (!hasNumber) {
            String s = scanner.nextLine();
            try {
                response = Integer.parseInt(s);
                hasNumber = true;
            } catch (NumberFormatException e) {
                if (s.equals("quit")) {
                    System.exit(0);
                }
                print("Not a number try again.");
            }
        }
        return response;
    }

    public void print(String s) {
        System.out.println(s);
    }

    public String getString(String request) {
        System.out.println(request);
        String response = scanner.nextLine();
        if (response.equals("quit")) {
            System.exit(0);
        }
        return response;
    }

    public int getInt(String s, int min, int max) {
        while (true) {
            int response = getInt(s);
            if (min <= response && response < max) {
                return response;
            } else print("out of bounds try again");
        }
    }
}
