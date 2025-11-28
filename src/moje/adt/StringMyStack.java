package moje.adt;

import java.util.Arrays;

public class StringMyStack implements MyStack {
    private static final int RESIZE_FACTOR = 2;

    private String[] data;
    private int top;

    public StringMyStack(int size) {
        this.data = new String[size];
        top = -1;
    }

    @Override
    public void push(String value) {
        //data[top + 1] = value;
        //top++;
        //je to to stejné
        if (top >= data.length - 1){
            data = Arrays.copyOf(data, data.length * RESIZE_FACTOR);
        }
        data[++top] = value;
    }

    @Override
    public String pop() {
        if (isEmpty()){
            System.out.println("Empty Stack");
            return null;
        }
        String toReturn = data[top];
        top--;
        return toReturn;
    }

    @Override
    public String peek() {
        if (isEmpty()){
            System.out.println("Empty Stack");
            return null;
        }
        return data[top];
    }

    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    @Override
    public int size() {
        return top+1;
    }

    public static void main(String[] args) {
        MyStack zasobník = new StringMyStack(100);
        for (int i = 1; i < 10000; i++) {
            zasobník.push(String.format("page %d", i));
        }

        while (!zasobník.isEmpty()){
            System.out.println(zasobník.pop());
        }

    }
}
