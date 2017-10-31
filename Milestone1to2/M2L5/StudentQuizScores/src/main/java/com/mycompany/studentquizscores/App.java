/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.studentquizscores;

import java.util.*;

/**
 *
 * @author James
 */
public class App {

    public static void main(String[] args) {

        boolean playAgain = true;
        // instantiate the class that implements the interface
        UserIOImpl myIO = new UserIOImpl();
        // instantiate the scanner
        Scanner myScanner = new Scanner(System.in);

        // create ArrayLists
        ArrayList<Integer> scores1 = new ArrayList<>();
        ArrayList<Integer> scores2 = new ArrayList<>();
        ArrayList<Integer> scores3 = new ArrayList<>();
        ArrayList<Integer> scores4 = new ArrayList<>();
        ArrayList<Integer> scores5 = new ArrayList<>();

        // populate ArrayList of scores
        scores1.add(80);
        scores1.add(92);
        scores1.add(87);

        scores2.add(59);
        scores2.add(63);
        scores2.add(68);

        scores3.add(99);
        scores3.add(100);
        scores3.add(95);

        scores4.add(75);
        scores4.add(77);
        scores4.add(79);

        scores5.add(85);
        scores5.add(87);
        scores5.add(82);

        // instantiate new Students (scores)
        Students stu1 = new Students(scores1);
        Students stu2 = new Students(scores2);
        Students stu3 = new Students(scores3);
        Students stu4 = new Students(scores4);
        Students stu5 = new Students(scores5);

        // create HashMap and populate it
        Map<String, Students> myHashMap = new HashMap<>();

        myHashMap.put("Bob", stu1);
        myHashMap.put("Fred", stu2);
        myHashMap.put("Alice", stu3);
        myHashMap.put("Jenny", stu4);
        myHashMap.put("John", stu5);

        // create keySet
        Set<String> keys = myHashMap.keySet();

        //Print formatted table
        while (playAgain == true) {

            myIO.print("");
            myIO.print("OPTIONS:");
            int menuChoice = myIO.readInt(" 1 - List of students \n 2 - Add a student \n 3 - Delete a student \n 4 - List of scores for a student, with avg score \n 5 - Exit");

// menu logic
            if (menuChoice == 1) {
                myIO.print("");
                myIO.print("STUDENTS");
                myIO.print("=====================");
                for (String k : keys) {
                    myIO.print(k);
                }
// add a student                
            } else if (menuChoice == 2) {
                ArrayList newScores = new ArrayList<>();
                Students newStudent = new Students(newScores);
                String studentName = myIO.readString("Enter a new student name");
                myHashMap.put(studentName, newStudent);
// remove a student                
            } else if (menuChoice == 3) {
                String removeName = myIO.readString("Enter the name of a student to remove:");
                Iterator<String> myIt = keys.iterator();
                while (myIt.hasNext()) {
                    String a = myIt.next();
                    if (a.equals(removeName)) {
                        myIt.remove();
                    }
                }
// print list of scores and average for a student            
            } else if (menuChoice == 4) {
                String studentChoice = myIO.readString("Enter students name (key)");
                for (String k : keys) {
                    if (studentChoice.equalsIgnoreCase(k)) {
                        myIO.print(myHashMap.get(k).getQuizScores() + "");
                     //   myIO.print("Average score: " + myHashMap.get(k).getAverage());
                    }
                }
// exit            
            } else if (menuChoice == 5) {
                myIO.print("Thank you, goodbye");
                playAgain = false;
            }
        }
    }
}
