package moje.adt;

public interface MyQueue<E> {
    void offer(E input);

    E poll();

    E peek();

}
