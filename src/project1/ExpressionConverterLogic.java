// CMSC 350 Data Structures and Analysis
// Project 1
// Evan Martin
// November 3, 2020

// This class tokenizes a prefix or postfix expression and
// performs the necessary logic for conversion.

package project1;

import java.util.*;

public class ExpressionConverterLogic {

    static ArrayList<String> tokenizedExpression = new ArrayList<>();

    static String evaluatePrefixToPostfix(String expression) throws SyntaxError {

        tokenizedExpression = tokenizedExpression(expression);
        Stack<String> reversalStack = new Stack<>();
        Stack<String> operandStack = new Stack<>();

        for (String s : tokenizedExpression) reversalStack.push(s);

        while (!reversalStack.isEmpty()) {
            String s = reversalStack.pop();
            if (!isOperator(s)) {
                operandStack.push(s);
            } else {
                if (operandStack.isEmpty()) {
                    throw new SyntaxError();
                }
                String str = operandStack.pop() + " " + operandStack.pop() + " " + s;
                operandStack.push(str);

            }
        }
        if (operandStack.isEmpty()) {
            throw new SyntaxError();
        } else return operandStack.pop();
    }

    static String evaluatePostfixToPrefix(String expression) throws SyntaxError {

        tokenizedExpression = tokenizedExpression(expression);
        Stack<String> tokenStack = new Stack<>();
        Stack<String> operandStack = new Stack<>();

        for (int i = tokenizedExpression.size() - 1; i >= 0; i--) {
            tokenStack.push(tokenizedExpression.get(i));
        }

        while (!tokenStack.isEmpty()) {
            String s = tokenStack.pop();
            if (!isOperator(s)) {
                operandStack.push(s);
            } else {
                if (operandStack.isEmpty()) {
                    throw new SyntaxError();
                }
                String firstPop = operandStack.pop();
                String secondPop = operandStack.pop();
                String str = s + " " + secondPop + " " + firstPop;
                operandStack.push(str);
            }
        }

        if (operandStack.isEmpty()) {
            throw new SyntaxError();
        } else return operandStack.pop();
    }


    private static ArrayList<String> tokenizedExpression(String expression) {

        ArrayList<String> tokenList = new ArrayList<>();
        char[] expressionAsArray = expression.toCharArray();
        StringBuilder token = new StringBuilder();

        for (char c : expressionAsArray) {

            if (!isOperator(c) && c != ' ') {
                token.append(c);

            } else if (isOperator(c) && (token.length() == 0)) {
                tokenList.add(String.valueOf(c));

            } else if (isOperator(c) && (token.length() > 0)) {
                tokenList.add(token.toString());
                tokenList.add(String.valueOf(c));
                token = new StringBuilder();

            } else if (token.length() > 0) {
                tokenList.add(token.toString());
                token = new StringBuilder();
            }
        }
        if (token.length() > 0) {
            tokenList.add(token.toString());
        }

        return tokenList;
    }

    static boolean isOperator(Character c) {
        return switch (c) {
            case '+', '-', '/', '*' -> true;
            default -> false;
        };
    }

    static boolean isOperator(String s) {
        return switch (s) {
            case "+", "-", "/", "*" -> true;
            default -> false;
        };
    }

}
