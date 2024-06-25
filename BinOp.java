import java.util.Objects;

public class BinOp implements Expr {
    Expr left;
    String operation;
    Expr right;

    public BinOp(Expr one, String op, Expr two){
        this.left = one;
        this.right = two;
        this.operation = op;
    }

    public double evaluate(){
        double valueLeft = this.left.evaluate();
        double valueRight = this.right.evaluate();
        // Now that we computed the left and right expressions, we can now evaluate the whole thing.

        return switch (this.operation) {
            case "+" -> valueLeft + valueRight;
            case "-" -> valueLeft - valueRight;
            case "*" -> valueLeft * valueRight;
            case "/" -> valueLeft / valueRight;
            case "^" -> Math.pow(valueLeft, valueRight);
            case null, default -> 0;
        };
    }
}
