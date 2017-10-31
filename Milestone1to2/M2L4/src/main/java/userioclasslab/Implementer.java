/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userioclasslab;

/**
 *
 * @author James
 */
import java.util.Scanner;

public class Implementer implements UserIO {

    Scanner myScanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        double num = 0;
        boolean inputIsValid = false;

        while (!inputIsValid) {
            print(prompt);

            if (myScanner.hasNextDouble()) {
                inputIsValid = true;
            } else {
                myScanner.nextLine();
                continue;
            }
            num = myScanner.nextDouble();
            myScanner.nextLine();
        }
        return num;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double num = 0;
        boolean inputIsValid = false;

        while (!inputIsValid) {
            print(prompt);

            if (myScanner.hasNextDouble()) {
                inputIsValid = true;
            } else {
                myScanner.nextLine();
                continue;
            }
            num = myScanner.nextDouble();
            myScanner.nextLine();

            if (num < min || num > max) {
                inputIsValid = false;
            }
        }
        return num;
    }

    @Override
    public float readFloat(String prompt) {

    }

    @Override
    public float readFloat(String prompt, float min, float max) {

    }

    @Override
    public int readInt(String prompt) {

    }

    @Override
    public int readInt(String prompt, int min, int max) {

    }

    @Override
    public long readLong(String prompt) {

    }

    @Override
    public long readLong(String prompt, long min, long max) {

    }

    @Override
    public String readString(String prompt) {

    }
}
