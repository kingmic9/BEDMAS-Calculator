import java.util.*;


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
        this.expression += " " + op;
    }


    /*
    * Add a number onto the expression
    * */
    public void AddNum(String num){
        this.expression += " " + num;
    }


    /*
    * Strip away any leading or trailing spaces before attempting to evaluate the string
    * */
    public void FormatExpression(){
        this.expression = this.expression.strip();
    }


    /*
    * Tokenize the expression and evaluate it, set the string to the result of the calculation
    * */
    public void Evaluate(){

        if (!(this.expression == "")) {
            // Then there is something to evaluate
            FormatExpression();
            String[] arguments = this.expression.split(" ");
            // Base Case just one number to evaluate
            if (arguments.length == 1) {
                Expr result = new Num(Double.parseDouble(arguments[0]));
                SetString(Double.toString(result.evaluate()));
                // This will effectively evaluate something everytime 3 things are typed... i.e. left, operation and right.

                // For two args, nothing needs to happen as the string stays as is
            } else if (arguments.length == 3) {
                // Attempt to evaluate expression

                Expr one = new Num(Double.parseDouble(arguments[0]));
                Expr two = new Num(Double.parseDouble(arguments[2]));
                BinOp toEval = new BinOp(one, arguments[1] ,two);

                SetString(Double.toString(toEval.evaluate()));
            }

        }


    }
}
