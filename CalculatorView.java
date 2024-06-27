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
        JPanel panel = new JPanel();
        panel.setBackground(Color.blue);

        // Set up the text field
        JTextField text = new JTextField(17);

        // Set font size
        Font font = new Font("Arial", Font.PLAIN, 25); // Adjust the font size (20) as needed
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
                            "1", "2", "3", "―", "^",
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
                case "―":
                case "^":
                    button.addActionListener(e -> {
                        this.calculatorModel.AddOp(symbol);
                        text.setText(this.calculatorModel.expression);
                    });
                    break;
                case "=":
                    button.addActionListener(e -> {
                        this.calculatorModel.Evaluate();
                        text.setText(this.calculatorModel.expression);
                    });
                    break;
                default:
                    // Handle default case if needed
                    break;
            }

            gridPanel.add(button);
        }

        // Make a seperate button grid for clearing and backspace.
        GridLayout clearing = new GridLayout(1, 2, 5, 5);
        JPanel lastPanel = new JPanel(clearing);
        lastPanel.setBackground(Color.BLUE);

        // Put in the clear and backspace buttons.
        JButton clear = new JButton("C");
        clear.setFont(new Font("Arial", Font.PLAIN, 25));
        clear.setFocusable(false);
        clear.setPreferredSize(new Dimension(70, 70));
        clear.setAlignmentX(Component.RIGHT_ALIGNMENT); // Center-align buttons
        clear.addActionListener(e -> {this.calculatorModel.SetString("");
            text.setText(this.calculatorModel.expression);});
        lastPanel.add(clear);

        JButton back = new JButton("<=");
        back.setFont(new Font("Arial", Font.PLAIN, 25));
        back.setFocusable(false);
        back.setPreferredSize(new Dimension(70, 70));
        back.setAlignmentX(Component.RIGHT_ALIGNMENT); // Center-align buttons
        back.addActionListener(e -> {this.calculatorModel.SetString("");
            text.setText(this.calculatorModel.expression);});
        lastPanel.add(back);

        panel.add(lastPanel, BorderLayout.EAST);



        panel.add(gridPanel);



        frame.add(panel, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(400, 500));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorView(new CalculatorModel());
    }

}

