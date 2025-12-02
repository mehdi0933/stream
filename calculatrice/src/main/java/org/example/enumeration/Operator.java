package org.example.enumeration;

public enum Operator {

    MULTIPLICATION("*"),
    DIVISION("/"),
    ADDITION("+"),
    SOUSTRACTION("-");

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
      }

    public static Operator fromSymbol(String symbol) {
        for (Operator op : values()) {
            if (op.symbol.equals(symbol)) return op;
        }
        throw new IllegalArgumentException("Symbole inconnu : " + symbol);
    }
}
