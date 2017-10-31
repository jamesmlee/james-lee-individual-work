/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shapesandperimeters;

/**
 *
 * @author James
 */
public class Square extends Shape {
    private double side;
    
    @Override public double getArea() {
        double area = side * side;
        return area;
    }
    
    @Override public double getPerimeter() {
        double perimeter = side * 4;
        return perimeter;
    }
    
    public double getSide() {
        return side;
    }
    
    public void setSide(double sideInput) {
        this.side = sideInput;
    }
}
