// CMSC 350 Data Structures and Analysis
// Project 1
// Evan Martin
// November 3, 2020

// This class creates a GUI that allows users to input and expression
// in either prefix or postfix and convert to the opposite expression.
// This class uses action listeners to trigger methods in the
// ExpressionConverterLogic class. It also catches any syntax errors.

package project1;

import java.awt.*;
import java.util.EmptyStackException;
import javax.swing.*;

public class ExpressionConverterGUI extends NiceFrame {

    public ExpressionConverterGUI() {
        super("Expression Converter", 400, 150);
        add(new ExpressionConverterPanel());
    }

    static public void main(String[] args) {
        ExpressionConverterGUI ecApp = new ExpressionConverterGUI();
        ecApp.display();
    }
}

class NiceFrame extends JFrame {

    public NiceFrame(String title, int width, int height) {
        super(title);
        setFrame(width, height);
    }

    public void display() {
        setVisible(true);
    }

    private void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class ExpressionConverterPanel extends JPanel {

    private String expression;

    public ExpressionConverterPanel() {

        setLayout(new GridLayout(3, 1));

        //create expression component
        JLabel expressionLabel = new JLabel("Enter Expression ", JLabel.LEFT);
        JTextField expressionText = new JTextField(15);

        //create buttons
        JButton prefixToPostfixButton = new JButton("Prefix to Postfix");
        JButton postfixToPrefixButton = new JButton("Postfix to Prefix");

        //create result component
        JLabel resultLabel = new JLabel("Result ", JLabel.LEFT);
        JTextField resultText = new JTextField(15);
        resultText.setEditable(false);

        //create the expression panel
        JPanel expressionPanel = new JPanel();
        expressionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        expressionPanel.add(expressionLabel);
        expressionPanel.add(expressionText);
        add(expressionPanel);

        //create the buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(prefixToPostfixButton);
        buttonsPanel.add(postfixToPrefixButton);
        add(buttonsPanel);

        //create the result panel
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        resultPanel.add(resultLabel);
        resultPanel.add(resultText);
        add(resultPanel);

        //prefix to postfix button action listener
        prefixToPostfixButton.addActionListener(e -> {
            expression = expressionText.getText();
            resultText.setText(actionButtonTryBlock(expression, true));
        });

        //postfix to prefix button action listener
        postfixToPrefixButton.addActionListener(e -> {
            expression = expressionText.getText();
            resultText.setText(actionButtonTryBlock(expression, false));
        });
    }

    private String actionButtonTryBlock(String expression, boolean prefixToPostfixSelector) {

        try {
            if (prefixToPostfixSelector) {
                return ExpressionConverterLogic.evaluatePrefixToPostfix(expression);
            } else {
                return ExpressionConverterLogic.evaluatePostfixToPrefix(expression);
            }
        } catch (SyntaxError | EmptyStackException syntaxError) {
            JOptionPane.showMessageDialog(null,
                    "Syntax Error.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return "error";
        }
    }
}
