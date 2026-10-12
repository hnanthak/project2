public record BinopNode(String operator, AST left, AST right) implements AST{
    @Override
    public double eval(){

        double lval= left.eval();
        double rval= right.eval();

        switch(operator){
            case "+":
                return lval+rval;
            case "-":
                return lval-rval;
            case "*":
                return lval*rval;
            case "/":
                return lval/rval;
            case "^":
                return  Math.pow(lval,rval);
            default:
                throw new IllegalArgumentException("invalid operatir");

        }
    }
}
