package br.com.erudio.apiresterudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.apiresterudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.apiresterudio.models.enums.CalculatorType;
import br.com.erudio.apiresterudio.services.util.NumericUtil;

@Service
public class MathService {

    @Autowired
    private NumericUtil numeric;

    public void Cal(String numberOne, String numberTwo, CalculatorType type) {

        if (!numeric.isNumeric(numberOne) || !numeric.isNumeric(numberTwo)) {
            exptionThrown();
        }

        switch (type) {
            case SUM:
                sum(numberOne, numberTwo);
                break;
            case MULTIPLY:
                multiply(numberOne, numberTwo);
                break;
            case DIVIDE:
                divide(numberOne, numberTwo);
                break;
            case SUBTRACT:
                subtract(numberOne, numberTwo);
                break;
            case AVARAGE:
                average(numberOne, numberTwo);
            default:
                exptionThrown();
                break;
        }

    }

    public Double sum(String numberOne, String numberTwo) {

        return numeric.convertToDouble(numberOne) + numeric.convertToDouble(numberTwo);
    }

    public Double multiply(String numberOne, String numberTwo) {

        return numeric.convertToDouble(numberOne) * numeric.convertToDouble(numberTwo);
    }

    public Double divide(String numberOne, String numberTwo) {

        return numeric.convertToDouble(numberOne) / numeric.convertToDouble(numberTwo);
    }

    public Double subtract(String numberOne, String numberTwo) {

        return numeric.convertToDouble(numberOne) - numeric.convertToDouble(numberTwo);
    }

    public Double average(String numberOne, String numberTwo) {

        return numeric.convertToDouble(numberOne) - numeric.convertToDouble(numberTwo);
    }

    public Double source(String number) {

        if (!numeric.isNumeric(number)) {
            exptionThrown();
        }
        return Math.sqrt(numeric.convertToDouble(number));
    }

    private void exptionThrown() {
        throw new UnsupportedMathOperationException("Please se a numeric value!");
    }

}
