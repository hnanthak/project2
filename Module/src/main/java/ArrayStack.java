public class ArrayStack<T> { //T for the data stored
    private Object[] array; //stores the stacks items as objects
    private int size; //keeps a track of items currently in the stack

    private ArrayStack() {
        this.array = new Object[10]; //holds 10 items
        this.size = 0; //the stack is empty so starts with 0 items
    }

    public static <T> ArrayStack<T> emptyStack() { //emty stack
        return new ArrayStack<T>(); //return new stack
    }

    public void push(T value) {
        if(size == array.length) { //is the array full or not
            Object[] newArray = new Object[array.length * 2]; //the size of the array doubles

            for(int i = 0; i < array.length; i++) {
                newArray[i] = array[i]; //the old items are copied into the new one
            }
            array = newArray; //the bigger array is used
        }
        array[size] = value;
        size++;
    }

    public T pop() {
        if(size ==0) {
            throw new IndexOutOfBoundsException();
        }
        T value = (T) array[size -1]; //the top item is converted to type T
        array[size - 1] = null; //the area is cleared where the item was stored
        size--;
        return value; //the removed item is given
    }

    public T peek() {
        if(size == 0) { //is the stack empty or not
            throw new IndexOutOfBoundsException();
        }
        return (T) array[size - 1]; //the top item is returned
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

}
