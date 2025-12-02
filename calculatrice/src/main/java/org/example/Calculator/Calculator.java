
package org.example.Calculator;

import org.example.calculeInterface.Calcule;

public class Calculator {

    private static Calculator instance;
    private Calcule calcule;

    private Calculator(Calcule calcule) {
        this.calcule = calcule;
    }

    public static Calculator getInstance(Calcule calcule) {
        if (instance == null) {
            instance = new Calculator(calcule);
        } else {
            instance.setStrategy(calcule);
        }
        return instance;
    }

    public void setStrategy(Calcule calcule) {

        this.calcule = calcule;
    }

    public int calculatorOperation(int... args) {

        return calcule.calcule(args);
    }
}
