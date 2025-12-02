package org.example.calculeStrategy;

import org.example.calculeInterface.Calcule;

public class Division implements Calcule {
    @Override
    public int calcule(int... arg) {
        int quotient = arg[0];
        for(int division = 1; division < arg.length; division++){
            quotient/=arg[division];
        }
        return quotient;

    }
}
