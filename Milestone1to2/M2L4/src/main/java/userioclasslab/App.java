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
public class App implements UserIO{
    public static void main(String[] args) {
        UserInterface mySelections = new UserInterface();
        mySelections.getInputs();
    }
}
