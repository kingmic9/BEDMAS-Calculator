import java.util.*;
import java.util.Stack;

/*
* This implementation of the String Evaluator was inspired by
* https://www.geeksforgeeks.org/how-to-evaluate-math-expression-given-in-string-form-in-java/
*
* My implementation adds the functionality of exponents!
* My implementation also adds the support for negative numbers. I.e. -12.0 if attempted to evaluate would crash
* My implementation uses the - and ― to differentiate between part of a number like -12 vs. operation (needing two operands)
*
* It does not have any malformed string error checking which breaks the program
* Therefore, I did have to implement some error catching in the main Calculator Application
* */

public class StringEvaluator {
        // Functions to evaluate a mathematical expression using 'BEDMAS' given
        // in string form
        public static double evaluateExpression(String expression) {
            char[] tokens = expression.toCharArray();

            // Stacks to store operands and operators
            Stack<Double> values = new Stack<>();
            Stack<Character> operators = new Stack<>();

            // Iterate through each character in the expression
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i] == ' ')
                    continue;

                // If the character is a digit or a decimal
                // point, parse the number
                if ((tokens[i] == '-' || tokens[i] >= '0' && tokens[i] <= '9')
                        || tokens[i] == '.' ) {
                    StringBuilder sb = new StringBuilder();
                    // Continue collecting digits and the
                    // decimal point to form a number
                    while (i < tokens.length
                            && (Character.isDigit(tokens[i]) || (tokens[i] == '-')
                            || tokens[i] == '.')) {
                        sb.append(tokens[i]);
                        i++;
                    }
                    // Parse the collected number and push it to
                    // the values stack
                    values.push(
                            Double.parseDouble(sb.toString()));
                    i--; // Decrement i to account for the extra
                    // increment in the loop
                }
                else if (tokens[i] == '(') {
                    // If the character is '(', push it to the
                    // operators stack
                    operators.push(tokens[i]);
                }
                else if (tokens[i] == ')') {
                    // If the character is ')', pop and apply
                    // operators until '(' is encountered
                    while (operators.peek() != '(') {
                        values.push(applyOperator(
                                operators.pop(), values.pop(),
                                values.pop()));
                    }
                    operators.pop(); // Pop the '('
                }
                else if (tokens[i] == '+' || tokens[i] == '―'
                        || tokens[i] == '*'
                        || tokens[i] == '/' || tokens[i] == '^') {
                    // If the character is an operator, pop and
                    // apply operators with higher precedence
                    while (!operators.isEmpty()
                            && hasPrecedence(tokens[i],
                            operators.peek())) {
                        values.push(applyOperator(
                                operators.pop(), values.pop(),
                                values.pop()));
                    }
                    // Push the current operator to the
                    // operators stack
                    operators.push(tokens[i]);
                }
            }

            // Process any remaining operators in the stack
            while (!operators.isEmpty()) {
                values.push(applyOperator(operators.pop(),
                        values.pop(),
                        values.pop()));
            }

            // The result is the only remaining element in the
            // values stack
            return values.pop();
        }

        // Function to check if operator1 has higher precedence
        // than operator2
        private static boolean hasPrecedence(char operator1,
                                             char operator2)
        {
            if (operator2 == '(' || operator2 == ')')
                return false;
            if (operator1 == '^') {
                return false;
            }
            return (operator1 != '*' && operator1 != '/')
                    || (operator2 != '+' && operator2 != '―');
        }

        // Function to apply the operator to two operands
        private static double applyOperator(char operator,
                                            double b, double a)
        {
            switch (operator) {
                case '+':
                    return a + b;
                case '―':
                    return a - b;
                case '*':
                    return a * b;
                case '/':
                    if (b == 0)
                        throw new ArithmeticException(
                                "Cannot divide by zero");
                    return a / b;
                case '^': return Math.pow(a, b);
            }
            return 0;
        }
}
