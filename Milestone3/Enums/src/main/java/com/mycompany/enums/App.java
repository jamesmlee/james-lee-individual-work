/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author James
 */
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Choose a day of the week");

        String userChoice = myScanner.nextLine();
        DaysOfWeek days = DaysOfWeek.valueOf(myScanner.nextLine().toUpperCase());
        
        
//        calculate(PLUS, 1, 1);
        
        
    }

    public enum DaysOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public void daysToFriday(DaysOfWeek days) {
        switch (days) {
            case MONDAY:
                System.out.println(4);
                break;
            case TUESDAY:
                System.out.println(3);
                break;
            case WEDNESDAY:
                System.out.println(2);
                break;
            case THURSDAY:
                System.out.println(1);
                break;
            case FRIDAY:
                System.out.println(0);
                break;
            case SATURDAY:
                System.out.println(6);
                break;
            case SUNDAY:
                System.out.println(5);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    
    
    
    public enum MathOperator {
        PLUS, MINUS, MULTIPLY, DIVIDE
    }

    public class IntMath {

        public int calculate(MathOperator operator, int operand1, int operand2) {
            switch (operator) {
                case PLUS:
                    return operand1 + operand2;
                case MINUS:
                    return operand1 - operand2;
                case MULTIPLY:
                    return operand1 * operand2;
                case DIVIDE:
                    return operand1 / operand2;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

}
