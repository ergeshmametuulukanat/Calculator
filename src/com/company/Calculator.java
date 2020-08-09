package com.company;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    static Plus plus = new Plus();
    static Minus minus = new Minus();
    static Multiplication multiplication = new Multiplication();
    static Division division = new Division();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String condition = scanner.nextLine();
        int firstNumber;
        int secondNumber;
        char operation = getOperation(condition);
        isCorrectOperation(operation);

        List<String> numbers = getNumbers(condition);
        String tempFirstNumber = numbers.get(0);
        String tempSecondNumber = numbers.get(1);

        boolean isRomanNumber = checkForRomanNumber(tempFirstNumber, tempSecondNumber);

        firstNumber = parseInt(tempFirstNumber);
        secondNumber = parseInt(tempSecondNumber);

        checkNumbersSize(firstNumber, secondNumber);

        if (!isRomanNumber) {
            firstNumber = getMinusFirstNumber(condition, firstNumber);
            secondNumber = getMinusSecondNumber(condition, secondNumber);
        } else {
            checkForMinusFirstRomanNumber(condition);
            checkForMinusSecondRomanNumber(condition);
        }

        int arabicResult = getResult(operation, firstNumber, secondNumber);

        if (isRomanNumber) {
            checkRomansResultForMinusNumber(arabicResult);
            System.out.println(Roman.arabicToRoman(arabicResult));
        } else {
            System.out.println(arabicResult);
        }
    }

    private static void isCorrectOperation(char operation) throws Exception {
        if (operation != '+' && operation != '-' && operation != '*' && operation != '/') {
            throw new Exception("Wrong operation");
        }
    }

    private static void checkRomansResultForMinusNumber(int arabicResult) throws Exception {
        if (arabicResult <= 0) {
            throw new Exception("Roman number should be more than 0");
        }
    }

    private static boolean checkForRomanNumber(String firstNumber, String secondNumber) throws Exception {
        if (isNumeric(firstNumber) && !isNumeric(secondNumber)
                || !isNumeric(firstNumber) && isNumeric(secondNumber)) {
            throw new Exception("Wrong input");
        }
        return !isNumeric(firstNumber) && !isNumeric(secondNumber);
    }

    private static int parseInt(String number) {
        return isNumeric(number) ? Integer.parseInt(number) : Arabic.romanToArabic(number);
    }

    private static void checkNumbersSize(int firstNumber, int secondNumber) throws Exception {
        if (firstNumber > 10 || secondNumber > 10) {
            throw new Exception("Numbers should be less than 10");
        }
    }

    private static int getResult(char operation, int firstNumber, int secondNumber) {
        int result = 0;
        switch (operation) {
            case '+' -> result = plus.operation(firstNumber, secondNumber);
            case '-' -> result = minus.operation(firstNumber, secondNumber);
            case '*' -> result = multiplication.operation(firstNumber, secondNumber);
            case '/' -> result = division.operation(firstNumber, secondNumber);
        }
        return result;
    }

    private static boolean isNumeric(String str) {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    private static List<String> getNumbers(String str) {
        StringBuilder tempSecondNumber = new StringBuilder();
        StringBuilder tempFirstNumber = new StringBuilder();
        str = str.replaceAll(" ", "");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '+' && str.charAt(i) != '-'
                    && str.charAt(i) != '*' && str.charAt(i) != '/') {
                tempSecondNumber.append(str.charAt(i));
            } else {
                tempFirstNumber = tempSecondNumber;
                tempSecondNumber = new StringBuilder();
                if (str.charAt(i + 1) == '-') i++;
            }
        }
        List<String> list = new ArrayList<>();
        list.add(tempFirstNumber.toString());
        list.add(tempSecondNumber.toString());
        return list;
    }

    private static char getOperation(String str) {
        char operation = '%';
        str = str.replaceAll(" ", "");
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-'
                    || str.charAt(i) == '*' || str.charAt(i) == '/') {
                operation = str.charAt(i);
                break;
            }
        }
        return operation;
    }

    private static int getMinusFirstNumber(String condition, int firstNumber) {
        if (checkForMinusFirstNumber(condition)) {
            firstNumber *= -1;
        }
        return firstNumber;
    }

    private static boolean checkForMinusFirstNumber(String condition) {
        return condition.charAt(0) == '-';
    }

    private static int getMinusSecondNumber(String condition, int secondNumber) {
        if (checkForMinusSecondNumber(condition)) {
            secondNumber *= -1;
        }
        return secondNumber;
    }

    private static boolean checkForMinusSecondNumber(String condition) {
        condition = condition.replaceAll(" ", "");
        for (int i = 1; i < condition.length(); i++) {
            if (condition.charAt(i) == '+' || condition.charAt(i) == '-'
                    || condition.charAt(i) == '*' || condition.charAt(i) == '/') {
                if (condition.charAt(i + 1) == '-') {
                    return true;
                }
            }
        }
        return false;
    }

    private static void checkForMinusFirstRomanNumber(String condition) throws Exception {
        if (checkForMinusFirstNumber(condition)) {
            throw new Exception("Roman number should be positive");
        }
    }

    private static void checkForMinusSecondRomanNumber(String condition) throws Exception {
        if (checkForMinusSecondNumber(condition)) {
            throw new Exception("Roman number should be positive");
        }
    }
}
