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
public class App {
    public static void main(String[] args) {
// we are coding to an interface ... can choose which one        
        UserIO myIo = new UserIOImpl();
        myIo.print("Hello world");
        
        int enteredInt = myIo.readInt("Please enter an int");
        myIo.print("You entered " + enteredInt);
        
    }
}
