/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useriodemo;

/**
 *
 * @author James
 */
import java.util.Scanner;

// need to add logic for methods with min/max parameters

public class UserIOImpl implements UserIO {

    private Scanner sc = new Scanner(System.in);

    /**
     *
     * @param message
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        boolean validInput = false;
        double result = 0;
        while (validInput == false) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
                result = Double.parseDouble(userInput);
                validInput = true;
            } catch (NumberFormatException | NullPointerException e) {
                print("Please enter a valid number: ");
            }
        }
        return result;
    }

    /**
     *
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean validInput = false;
        double result = 0;
        while (validInput == false) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
                result = Double.parseDouble(userInput);
                validInput = true;
            } catch (NumberFormatException | NullPointerException e) {
                print("Please enter a valid number: ");
            }
        }
        return result;
    }

    /**
     *
     * @param prompt
     * @return
     */
    @Override
    public float readFloat(String prompt) {
        boolean validInput = false;
        float result = 0;
        while (validInput == false) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
                result = Float.parseFloat(userInput);
                validInput = true;
            } catch (NumberFormatException | NullPointerException e) {
                print("Please enter a valid number: ");
            }
        }
        return result;
    }

    /**
     *
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean validInput = false;
        float result = 0;
        while (validInput == false) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
                result = Float.parseFloat(userInput);
                validInput = true;
            } catch (NumberFormatException | NullPointerException e) {
                print("Please enter a valid number: ");
            }
        }
        return result;
    }

    /**
     *
     * @param prompt
     * @return
     */
    @Override
    public int readInt(String prompt) {
        boolean validInput = false;
        int result = 0;
        while (validInput == false) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
                result = Integer.parseInt(userInput);
                validInput = true;
            } catch (NumberFormatException | NullPointerException e) {
                print("Please enter a valid number: ");
            }
        }
        return result;
    }

    /**
     *
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    @Override
    public int readInt(String prompt, int min, int max) {
        boolean validInput = false;
        int result = 0;
        while (validInput == false) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
                result = Integer.parseInt(userInput);
                validInput = true;
            } catch (NumberFormatException | NullPointerException e) {
                print("Please enter a valid number: ");
            }
        }
        return result;
    }

    /**
     *
     * @param prompt
     * @return
     */
    @Override
    public long readLong(String prompt) {
        boolean validInput = false;
        long result = 0;
        while (validInput == false) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
                result = Long.parseLong(userInput);
                validInput = true;
            } catch (NumberFormatException | NullPointerException e) {
                print("Please enter a valid number: ");
            }
        }
        return result;
    }

    /**
     *
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    @Override
    public long readLong(String prompt, long min, long max) {
        boolean validInput = false;
        long result = 0;
        while (validInput == false) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
                result = Long.parseLong(userInput);
                validInput = true;
            } catch (NumberFormatException | NullPointerException e) {
                print("Please enter a valid number: ");
            }
        }
        return result;
    }

    /**
     *
     * @param prompt
     * @return
     */
    @Override
    public String readString(String prompt) {
        String userInput;
        print(prompt);
        userInput = sc.nextLine();
        return userInput;
    }
}
