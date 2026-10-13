public class parser {
    public static AST parsePostfix(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("emptyinput");
        }

        String[] tokens = input.trim().split("\\s+");
        ArrayStack<AST> stack = ArrayStack.emptyStack();
        for (String token : tokens) {
            try {
                double number = Double.parseDouble(token);
                stack.push(new NumNode(number));
            } catch (NumberFormatException e) {
                if (token.equals("+") ||
                        token.equals("-") ||
                        token.equals("*") ||
                        token.equals("/") ||
                        token.equals("^")) {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException(
                                "insufficient operands"
                        );
                    }
                    AST right = stack.pop();
                    AST left = stack.pop();
                    stack.push(new BinopNode(token, left, right));
                } else {
                    throw new IllegalArgumentException("invalid token");
                }
            }
        }
        return stack.pop();
    }

    private static boolean isOperator(String token) {
        return token.equals("+") ||
                token.equals("-") ||
                token.equals("*") ||
                token.equals("/") ||
                token.equals("^");
    }

    private static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }

    public static AST parseInfix(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("empty input");
        }

        ArrayStack<AST> values = ArrayStack.emptyStack();
        ArrayStack<String> operators = ArrayStack.emptyStack();

        String[] tokens = input.trim().split("\\s+");


        boolean expectingValue = true;

        for (String token : tokens) {
            try {
                double number = Double.parseDouble(token);

                if (!expectingValue) {
                    throw new IllegalArgumentException("too many operands");
                }

                values.push(new NumNode(number));
                expectingValue = false;

            } catch (NumberFormatException e) {

                if (token.equals("(")) {
                    if (!expectingValue) {
                        throw new IllegalArgumentException("too many operands");
                    }
                    operators.push(token);
                }
                else if (token.equals(")")) {

                    while (!operators.isEmpty() && !operators.peek().equals("(")) {
                        String operator = operators.pop();
                        if (values.size() < 2) {
                            throw new IllegalArgumentException("insufficient operands");
                        }
                        AST right = values.pop();
                        AST left = values.pop();
                        values.push(new BinopNode(operator, left, right));
                    }

                    if (operators.isEmpty()) {
                        throw new IllegalArgumentException("mismatched close paren");
                    }

                    operators.pop();

                    if (expectingValue) {throw new IllegalArgumentException("insufficient operands");
                    }

                    expectingValue = false;

                }
                else if (isOperator(token)) {

                    if (expectingValue) {
                        throw new IllegalArgumentException("insufficient operands");
                    }
                    while (!operators.isEmpty() && !operators.peek().equals("(") && (precedence(operators.peek()) > precedence(token) || (precedence(operators.peek()) == precedence(token) && !token.equals("^")))) {
                        String operator = operators.pop();
                        if (values.size() < 2) {
                            throw new IllegalArgumentException("insufficient operands");
                        }
                        AST right = values.pop();
                        AST left = values.pop();
                        values.push(new BinopNode(operator, left, right));
                    }
                    operators.push(token);
                    expectingValue = true;
                }
                else {
                    throw new IllegalArgumentException("invalid token");
                }
            }
        }
        while (!operators.isEmpty()) {

            String operator = operators.pop();
            if (operator.equals("(")) {
                throw new IllegalArgumentException("mismatched open paren");
            }

            if (values.size() < 2) {
                throw new IllegalArgumentException(
                        "insufficient operands");
            }

            AST right = values.pop();
            AST left = values.pop();

            values.push(new BinopNode(operator, left, right));
        }

        if (expectingValue) {
            throw new IllegalArgumentException("insufficient operands");
        }

        if (values.size() > 1) {
            throw new IllegalArgumentException("too many operands");
        }
        return values.pop();
    }
}
