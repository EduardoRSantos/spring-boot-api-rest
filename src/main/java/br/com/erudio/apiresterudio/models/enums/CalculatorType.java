package br.com.erudio.apiresterudio.models.enums;


public enum CalculatorType {
    
    SUM(1),
    MULTIPLY(2),
    DIVIDE(3),
    SUBTRACT(4),
    AVARAGE(5);

    private int code;

    private CalculatorType(int code) {
        this.code = code;
    }

}
