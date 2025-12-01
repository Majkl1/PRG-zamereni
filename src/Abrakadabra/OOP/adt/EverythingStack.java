package Abrakadabra.OOP.adt;

public class EverythingStack<E> {
    EverythingStackLink<E> top;

    void push(E value){
        EverythingStackLink<E> newTop = new EverythingStackLink<>();
        newTop.value = value;
        if (top == null){
            top = newTop;
        } else {
            newTop.next = top;
            top = newTop;
        }
    }


    E pop(){
        if (top == null){
            return null;
        }
        E toReturn = top.value;
        top = top.next;
        return toReturn;
    }


    E peek(){
        if (top == null){
            return null;
        }
        return top.value;
    }

    public static void main(String[] args) {

    }

}

class EverythingStackLink <E>{
    E value;
    EverythingStackLink<E> next;
}
