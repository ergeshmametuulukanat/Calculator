package com.company;

public class Multiplication implements ICalculator {
    @Override
    public int operation(int firstNumber, int secondNumber) {
        return firstNumber * secondNumber;
    }
}
