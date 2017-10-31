/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.statecapitals2;

/**
 *
 * @author James
 */
public class Capitals {
    private String capName;
    private int population;
    private float sqFootage;

    public Capitals(String capName, int population, float sqFootage) {
        this.capName = capName;
        this.population = population;
        this.sqFootage = sqFootage;
    }

    public String getCapName() {
        return capName;
    }

    public void setCapName(String capName) {
        this.capName = capName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getSqFootage() {
        return sqFootage;
    }

    public void setSqFootage(float sqFootage) {
        this.sqFootage = sqFootage;
    }  
}
