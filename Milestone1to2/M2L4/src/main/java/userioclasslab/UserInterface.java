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

public class UserInterface {

// anywhere I used scanner, now using Implementer    
    Implementer myImp = new Implementer();
    SimpleCalculator myCalculation = new SimpleCalculator();
    
    public void getInputs() {
        String userChoice = null;
        while (!"exit".equals(userChoice)) {
            
            System.out.println("This calculator can add, subtract, multiply, or divide 2 numbers");
            System.out.println("What do you want to do? Please choose: ");
            System.out.println("add, subtract, multiply, divide, exit");
            
            userChoice = myScanner.nextLine();
            
            if (userChoice.equals("exit")) {
                System.out.println("Thank you, goodbye");
            } else {
                System.out.println("What is your first number?");
                float value1 = myScanner.nextFloat();
                myScanner.nextLine();
                
                System.out.println("What is your second number?");
                float value2 = myScanner.nextFloat();
                myScanner.nextLine();
                
                if (userChoice.equals("add")) {
                    System.out.println("Your result is " + myCalculation.Add(value1, value2));
                } else if (userChoice.equals("subtract")) {
                    System.out.println("Your result is " + myCalculation.Subtract(value1, value2));
                } else if (userChoice.equals("divide")) {
                    System.out.println("Your result is " + myCalculation.Divide(value1, value2));
                } else {
                    System.out.println("Your result is " + myCalculation.Multiply(value1, value2));
                }
            }
        } 
    }
}
