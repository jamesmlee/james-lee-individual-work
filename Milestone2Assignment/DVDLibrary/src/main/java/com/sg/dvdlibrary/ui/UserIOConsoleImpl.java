/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

/**
 *
 * @author James
 */
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

// need to update every data type with min/max (readInt is ok)  
// need to initialize valid input
    boolean validInput = false;
    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {

        double num = 0;
        while (validInput == false) {
            print(prompt);
            String doubleString = sc.nextLine();
            try {
                num = Double.parseDouble(doubleString);
                validInput = true;

            } catch (NumberFormatException e) {
                print("Please enter a valid number");
            }
        }
        return num;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        print(prompt);

        boolean isBetweenMaxAndMin = false;

        while (isBetweenMaxAndMin == false) {
            double d = sc.nextDouble();
            if (d > min && d < max) {
                return d;
            } else {
                print("Please enter a valid number");
            }
        }
        return 0;
    }

    @Override
    public float readFloat(String prompt) {
        float num = 0;
        while (validInput == false) {
            print(prompt);
            String doubleFloat = sc.nextLine();
            try {
                num = Float.parseFloat(doubleFloat);
                validInput = true;

            } catch (NumberFormatException e) {
                print("Please enter a valid float");
            }
        }
        return num;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        print(prompt);

        boolean isBetweenMaxAndMin = false;

        while (isBetweenMaxAndMin == false) {
            float f = sc.nextFloat();
            if (f > min && f < max) {
                return f;
            } else {
                print("Please enter a valid number");
            }
        }
        return 0;
    }

    @Override
    public int readInt(String prompt) {
        int num = 0;
        validInput = false;
        while (validInput == false) {
            print(prompt);
            String intString = sc.nextLine();
            try {
                num = Integer.parseInt(intString);
                validInput = true;

            } catch (NumberFormatException e) {
                print("Please enter a valid number");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        validInput = false;
        int num = 0;

        while (!validInput) {
            print(prompt);
            String s = sc.nextLine();
            try {
                num = Integer.parseInt(s);
                validInput = num >= min && num <= max;

            } catch (NumberFormatException e) {
                print("Please enter a valid number");
            }
        }
        return num;
    }

    @Override
    public long readLong(String prompt) {
        long num = 0;
        while (validInput == false) {
            print(prompt);
            String longString = sc.nextLine();
            try {
                num = Long.parseLong(longString);
                validInput = true;

            } catch (NumberFormatException e) {
                print("Please enter a valid number");
            }
        }
        return num;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        print(prompt);

        boolean isBetweenMaxAndMin = false;

        while (isBetweenMaxAndMin == false) {
            long l = sc.nextLong();
            if (l > min && l < max) {
                return l;
            } else {
                print("Please enter a valid number");
            }

        }
        return 0;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        validInput = false;
        LocalDate date = null;
        while (validInput == false) {
            print(prompt);
            String response = sc.nextLine();
            try {
                date = LocalDate.parse(response);
                validInput = true;
            } catch (DateTimeException e) {
                print("Please enter date in this format: yyyy-MM-dd");
            }
        }
        return date;
    }
    
    // read in user string input for $ and convert to BigDecmial
}