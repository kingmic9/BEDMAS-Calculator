import java.util.Scanner;

public class CalculatorModelTest {
    public static void main(String[] args) {
        CalculatorModel test = new CalculatorModel();
        test.expression = "5 + 2 * (2 + 3 * 2) + 2";
        test.Evaluate();
        System.out.print(test.expression);
    }
}
