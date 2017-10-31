/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.studentquizscores;

/**
 *
 * @author James
 */
import java.util.*;

// note that Students is just a class that contains quiz scores
// just think of the class as quiz scores
public class Students {

    private ArrayList<Integer> quizScores;
    private float average;

    public Students(ArrayList<Integer> quizScores) {
        this.quizScores = quizScores;
    }

    public float getAverage() {
        return average;
    }
    
    public void setAverage(float average) {
        this.average = average;
    }

    public ArrayList<Integer> getQuizScores() {
        return quizScores;
    }

    public void setQuizScores(ArrayList<Integer> quizScores) {
        this.quizScores = quizScores;
    }

}
