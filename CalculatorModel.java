import java.util.EmptyStackException;
import java.util.Stack;


public class CalculatorModel {
    // So the idea for this calculator is that the calculator builds a string
    // This string is displayed on the calculator screen, and then can be evaluated through tokenizing
    public String expression;

    public CalculatorModel(){
        // Right when this model is made, have it set the string expression to a empty string so no expression is
        // displayed.
        this.expression = "";
    }


    /*
    * Set the String for the Calculator Model to a new one / update an existing expression.
    * */
    public void SetString(String newString){
        this.expression = newString;
    }


    /*
    * Add an operation onto the expression
    * */
    public void AddOp(String op){
        this.expression += " " + op + " ";
    }


    /*
    * Add a number onto the expression
    * */
    public void AddNum(String num){
        this.expression += num;
    }


    /*
    * Tokenize the expression and evaluate it, set the string to the result of the calculation
    * */
    public void Evaluate() {
        // Use the string evaluator to evaluate the expression
        // Catch appropriate errors!
        // Handle Gracefully!
        try {
        this.expression = (StringEvaluator.evaluateExpression(this.expression)).toString();
        } catch (ArithmeticException e) {
            this.expression = "Error: Division by Zero!";
        } catch (EmptyStackException e) {
            this.expression = "Error: Invalid Input!";
        }
    }
}
