package moje.adt;

public class EverythingQueue<E> implements MyQueue<E>{
    EverythingLink<E> first, last;
    
    @Override
    public void offer(E input) {
        EverythingLink<E> toAdd = new EverythingLink<>();
        toAdd.value = input;
        if (first == null){
            first = toAdd;
            last = toAdd;
        } else {
            last.next = toAdd;
            last = toAdd;
        }
    }

    @Override
    public E poll() {
        E toReturn = first.value;
        first = first.next;
        return toReturn;
    }

    @Override
    public E peek() {
        return first.value;
    }
}

class EverythingLink<E>{
    E value;
    EverythingLink<E> next;
}