package com.company;

public class Division implements ICalculator {

    @Override
    public int operation(int firstNumber, int secondNumber) {
        return firstNumber / secondNumber;
    }
}
