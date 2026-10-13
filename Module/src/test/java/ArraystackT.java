import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ArrayStackTest {
    @Test
    void emptyStack() {
        ArrayStack<Integer> stack = ArrayStack.emptyStack();
        assertEquals(true, stack.isEmpty());
        assertEquals(0, stack.size());
    }


    @Test
    void push() {
        ArrayStack<Integer> stack = ArrayStack.emptyStack();
        stack.push(20);
        stack.push(30);
        assertEquals(2, stack.size());
        assertEquals(30, stack.peek());
    }


    @Test
    void pop() {
        ArrayStack<Integer> stack = ArrayStack.emptyStack();
        stack.push(20);
        stack.push(30);
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(0, stack.size());
    }


    @Test
    void peek() {
        ArrayStack<Integer> stack = ArrayStack.emptyStack();
        stack.push(20);
        assertEquals(20, stack.peek());
        assertEquals(1, stack.size());
    }


    @Test
    void popEmpty() {
        ArrayStack<Integer> stack = ArrayStack.emptyStack();
        assertThrows(IndexOutOfBoundsException.class, () -> stack.pop());
    }


    @Test
    void peekEmpty() {
        ArrayStack<Integer> stack = ArrayStack.emptyStack();
        assertThrows(IndexOutOfBoundsException.class, () -> stack.peek());
    }
}

