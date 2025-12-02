package org.example.calculeStrategy;

import org.example.calculeInterface.Calcule;

public class Addition implements Calcule {
    @Override
    public int calcule(int... arg) {
        int somme = 0;
        for(int addition = 0; addition < arg.length; addition++){
            somme+=arg[addition];
        }
        return somme;
    }
}
