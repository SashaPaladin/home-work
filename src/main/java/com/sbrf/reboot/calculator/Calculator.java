package com.sbrf.reboot.calculator;

public class Calculator {

    public int getAddition(int summand, int addend) {
        return summand + addend;
    }

    public int getSubtraction(int minuend, int subtrahend) {
        return minuend - subtrahend;
    }

    public int getMultiplication(int multiplicanda, int multiplier) {
        return multiplicanda * multiplier;
    }

    public int getDivision(int dividend, int divisor) {
        return dividend / divisor;
    }

    public double getPow(int base, int exponent) {
        return Math.pow(base, exponent);
    }

    public int getAbs(int a) {
        return Math.abs(a);
    }

    public int getMod(int dividend, int divisor) {
        return Math.floorMod(dividend, divisor);
    }

}
