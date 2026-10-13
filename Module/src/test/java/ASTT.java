import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ASTT {
    @Test

    void NumNode(){
        assertEquals(5.0,new NumNode(5).eval() );
    }

    @Test

    void BinopNode(){
        AST add= new BinopNode("+", new NumNode(3), new NumNode(4));
        assertEquals(7.0, add.eval());
    }

    @Test
    void testNestedAST(){
        AST tree = new BinopNode("*", new NumNode(2), new BinopNode("+", new NumNode(3), new NumNode(4)));
        assertEquals(14.0, tree.eval());
    }

    @Test
    void tPostFix(){
        AST result = parser.parsePostfix("3 4 +");
        assertEquals(7.0, result.eval());
    }

    @Test
    void tInfix(){
        AST result= parser.parseInfix("2 * ( 3 + 4 )");
        assertEquals(14.0,result.eval());
    }

    @Test
    void tExponent(){
        AST result=parser.parseInfix("2 ^ 3 ^ 2");
        assertEquals(512, result.eval());
    }

}
