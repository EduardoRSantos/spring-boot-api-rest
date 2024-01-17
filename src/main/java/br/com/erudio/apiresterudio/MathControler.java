package br.com.erudio.apiresterudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.erudio.apiresterudio.services.MathService;

@RestController
@RequestMapping("")
public class MathControler {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private MathService mMathService;

    @GetMapping("sum/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {

        Double response = mMathService.sum(numberOne, numberTwo);

        return ResponseEntity.ok().body(response);

    }

    @GetMapping("sub/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> sub(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {

        Double response = mMathService.subtract(numberOne, numberTwo);

        return ResponseEntity.ok().body(response);

    }

    @GetMapping("mult/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> mult(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {

        Double response = mMathService.multiply(numberOne, numberTwo);

        return ResponseEntity.ok().body(response);

    }

    @GetMapping("div/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> div(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {

        Double response = mMathService.divide(numberOne, numberTwo);

        return ResponseEntity.ok().body(response);

    }

    @GetMapping("med/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> med(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {

        Double response = mMathService.average(numberOne, numberTwo);

        return ResponseEntity.ok().body(response);

    }

    @GetMapping("raiz/{number}")
    public ResponseEntity<Double> raiz(
            @PathVariable(value = "number") String number) {

        Double response = mMathService.source(number);

        return ResponseEntity.ok().body(response);

    }

}
