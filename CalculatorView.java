import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView {
    CalculatorModel calculatorModel;


    public CalculatorView(CalculatorModel calculatorModel){
        this.calculatorModel = calculatorModel;
        // Set up the frame and name it
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Set up the background
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        panel.setBackground(Color.blue);

        // Set up the text field
        JTextField text = new JTextField(12);

        // Set font size
        Font font = new Font("Arial", Font.PLAIN, 36); // Adjust the font size (20) as needed
        text.setFont(font);

        // Make it immutable
        text.setEditable(false);

        text.setBorder(new EmptyBorder(0, 0, 0, 0));

        // Remove focus border
        text.setFocusable(false);

        panel.add(text);

        // Create a panel with a GridLayout
        GridLayout grid = new GridLayout(4, 5, 5, 5); // 4x4 grid with gaps between buttons

        JPanel gridPanel = new JPanel(grid);
        gridPanel.setBackground(Color.blue);

        // Add the buttons.

        String[] symbols = {"7", "8", "9", "*", "(",
                            "4", "5", "6", "/", ")",
                            "1", "2", "3", "–", "^",
                            "+/-", "0", ".", "+", "="};

        for (String symbol : symbols) {
            JButton button = new JButton(symbol);
            button.setFont(new Font("Arial", Font.PLAIN, 25));
            button.setFocusable(false);
            button.setPreferredSize(new Dimension(70, 70));
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align buttons

            // All those settings are for all buttons by default.

            // Now for the functionality.

            switch (symbol) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "0":
                case ".":
                case "(":
                case ")":
                    button.addActionListener(e -> {
                        this.calculatorModel.AddNum(symbol);
                        text.setText(this.calculatorModel.expression);
                    });
                    break;
                case "+/-":
                    button.addActionListener(e -> {
                        this.calculatorModel.AddNum("-");
                        text.setText(this.calculatorModel.expression);
                    });
                    break;
                case "*":
                case "/":
                case "+":
                case "–":
                case "^":
                    button.addActionListener(e -> {
                        this.calculatorModel.AddOp(symbol);
                        text.setText(this.calculatorModel.expression);
                    });
                    break;
                case "=":
                    button.addActionListener(e -> {
                        this.calculatorModel.Evaluate();

                        // If not needed to be a decimal value, keep it as an int
                        if (this.calculatorModel.expression.endsWith(".0")) {
                            int index = this.calculatorModel.expression.length() - 2;
                            // To remove both
                            this.calculatorModel.expression = this.calculatorModel.expression.substring(0, index);
                        }

                        if (this.calculatorModel.expression == "Infinity") {
                            this.calculatorModel.expression = "Error: Too Big!";
                        } else if (this.calculatorModel.expression == "-Infinity") {
                            this.calculatorModel.expression = "Error: Too Small!";
                        }
                        text.setText(this.calculatorModel.expression);
                    });
                    break;
                default:
                    // Handle default case if needed
                    break;
            }

            gridPanel.add(button);
        }

        // Make a separate button grid for clearing and backspace.
        JPanel lastPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        lastPanel.setBackground(Color.BLUE);

        // Put in the clear and backspace buttons.
        JButton clear = new JButton("C");
        clear.setFont(new Font("Arial", Font.PLAIN, 25));
        clear.setFocusable(false);
        clear.setPreferredSize(new Dimension(70, 70));
        clear.setAlignmentX(Component.RIGHT_ALIGNMENT); // align buttons
        clear.addActionListener(e -> {this.calculatorModel.SetString("");
            text.setText(this.calculatorModel.expression);});
        lastPanel.add(clear, BorderLayout.WEST);

        // Create the back-button
        JButton back = new JButton("←");
        back.setFont(new Font("Arial", Font.PLAIN, 25));
        back.setFocusable(false);
        back.setPreferredSize(new Dimension(70, 70));
        back.setAlignmentX(Component.RIGHT_ALIGNMENT); // align buttons
        back.addActionListener(e -> {
            // If the string is empty, do nothing
            if (this.calculatorModel.expression == ""){return;}

            // Figure out what is the last index in the string and pull that value.
            // The index of the last item will be length of the string - 1.
            // Pull that last item, check how many things to delete
            int lastIndex = this.calculatorModel.expression.length() - 1;
            char lastItem = this.calculatorModel.expression.charAt(lastIndex);
            if (lastItem == ' ') {
                // Remove the last 3 chars as it is something like ' + '
                this.calculatorModel.SetString(this.calculatorModel.expression.substring(0, lastIndex - 2));
                // End index is excluded.
            } else {
                // Just remove the last element
                this.calculatorModel.SetString(this.calculatorModel.expression.substring(0, lastIndex));}
            // Update the textfield in both cases.
            text.setText(this.calculatorModel.expression);});

        lastPanel.add(back, BorderLayout.WEST);

        // Add both sets of buttons to the text box
        panel.add(lastPanel, BorderLayout.WEST);
        panel.add(gridPanel);

        // Configure and add everything to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(397, 468));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorView(new CalculatorModel());
    }

}

