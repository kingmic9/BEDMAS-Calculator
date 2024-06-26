import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorModelTest {

    @Test
    void EvaluateNumber() {
        // Test that the String Evaluator can process a single string number.
        // Also ensure that the string is changed when the calculator attempts to evaluate it (changed to double)
        CalculatorModel x = new CalculatorModel();
        x.SetString("5");
        StringEvaluator.evaluateExpression(x.expression);
        assertEquals(5.0, StringEvaluator.evaluateExpression(x.expression));
        x.Evaluate();
        assertEquals("5.0", x.expression);
    }

    @Test
    void TestBasicArithmetic() {
        // Test All Basic Arithmetic
        CalculatorModel x = new CalculatorModel();

        // Addition
        x.SetString("5 + 2");
        x.Evaluate();
        assertEquals("7.0", x.expression);

        // Subtraction
        x.SetString("2 - 5");
        x.Evaluate();
        assertEquals("-3.0", x.expression);

        // Multiplication
        x.SetString("5 * 2");
        x.Evaluate();
        assertEquals("10.0", x.expression);

        // Division
        x.SetString("5 / 2");
        x.Evaluate();
        assertEquals("2.5", x.expression);

        // Exponents
        x.SetString("5 ^ 2");
        x.Evaluate();
        assertEquals("25.0", x.expression);
    }

    @Test
    void BEDMAS() {
        CalculatorModel x = new CalculatorModel();

        // Just a general test involving order of operations
        // 4 ^ 2 happens before it is added to 2
        // 2 * 3 happens before the rest of the additions as needed.
        x.SetString("5 + 2 * 3 + (2 + 4 ^ 2)");
        x.Evaluate();
        assertEquals("29.0", x.expression);

        // Test to make sure multiplication happens after ^
        x.SetString("5 * 2 ^ 2");
        x.Evaluate();
        assertEquals("20.0", x.expression);

        // Reverse order of previous test
        x.SetString("2 ^ 2 * 5");
        x.Evaluate();
        assertEquals("20.0", x.expression);

        // Reverse Addition Case
        x.SetString("2 ^ 2 + 2");
        x.Evaluate();
        assertEquals("6.0", x.expression);
    }
}