package com.company;

public class Minus implements ICalculator {

    @Override
    public int operation(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber;
    }
}
