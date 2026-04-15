import java.util.Stack;

public class ExpressionConverter {

    static int precedence(char ch) {
        switch (ch) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }

    public static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop());
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static String infixToPrefix(String exp) {
        // Step 1: Reverse infix
        StringBuilder revInfix = new StringBuilder(exp).reverse();
        
        // Step 2: Swap brackets
        for (int i = 0; i < revInfix.length(); i++) {
            if (revInfix.charAt(i) == '(') revInfix.setCharAt(i, ')');
            else if (revInfix.charAt(i) == ')') revInfix.setCharAt(i, '(');
        }

        // Step 3: Get postfix of reversed string
        String postfix = infixToPostfix(revInfix.toString());

        // Step 4: Reverse postfix to get prefix
        return new StringBuilder(postfix).reverse().toString();
    }

    public static void main(String[] args) {
        String exp = "a+b*(c^d-e)";
        System.out.println("Infix: " + exp);
        System.out.println("Postfix: " + infixToPostfix(exp));
        System.out.println("Prefix: " + infixToPrefix(exp));
    }
}                                                                                                                                                                             