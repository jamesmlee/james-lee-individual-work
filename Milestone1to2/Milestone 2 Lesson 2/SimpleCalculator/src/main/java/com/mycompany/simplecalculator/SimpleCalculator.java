/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplecalculator;

/**
 *
 * @author James
 */
public class SimpleCalculator {

    private float result;

    public float Add(float value1, float value2) {
        return result = value1 + value2;
    }

    public float Subtract(float value1, float value2) {
        return result = value1 - value2;
    }

    public float Divide(float value1, float value2) {
        return result = value1 / value2;
    }

    public float Multiply(float value1, float value2) {
        return result = value1 * value2;
    }
}
