public class parser {
    public static AST parsePostfix(String input) {

        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("empty input");
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

        if (stack.size() > 1) {
            throw new IllegalArgumentException("too many operands");
        }

        return stack.pop();
    }
}
