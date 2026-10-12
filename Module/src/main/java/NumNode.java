public record NumNode(double val) implements AST {
    @Override
    public double eval(){
        return val;
    }
}
