import java.util.Scanner;

public class CalculatorModelTest {
    public static void main(String[] args) {
        CalculatorModel test = new CalculatorModel();
        test.AddNum("5");
        test.AddOp("/");
        test.AddNum("2");
        test.Evaluate();
        test.AddOp("*");
        test.AddNum("3");
        test.Evaluate();
        System.out.print(test.expression);
    }
}
