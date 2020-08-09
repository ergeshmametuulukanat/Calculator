package com.company;

public class Plus implements ICalculator {
    @Override
    public int operation(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }
}
