package deque;

import java.util.Iterator;
import java.util.ListIterator;

public class LinkedListDeque<T> implements Deque<T>{
    private int size;


    public class Node{
        public Node prev;
        public Node next;
        public T item;
    }
    private Node firstNode;
    private Node lastNode;
    private int count;
    public LinkedListDeque(){
        size = 0;
        count = 0;
        firstNode = new Node();
        lastNode = new Node();
        firstNode.next = lastNode;
        lastNode.prev = firstNode;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void addFirst(T item){
        Node newnode = new Node();
        newnode.item = item;
        newnode.prev = firstNode;
        newnode.next = firstNode.next;
        firstNode.next.prev = newnode;
        firstNode.next = newnode;
        size += 1;
    }
    public void addLast(T item){
        Node newnode = new Node();
        newnode.item = item;
        newnode.next = lastNode;
        newnode.prev = lastNode.prev;
        lastNode.prev.next = newnode;
        lastNode.prev = newnode;
        size += 1;
    }

    @Override
    public void printDeque() {
        Node current = firstNode.next;
        for (int i = 0; i < size; i++){
            System.out.print(current.item);
            current = current.next;
        }
    }

    @Override
    public T removeFirst() {
        if (size <= 0){return null;}
        T item = firstNode.next.item;
        Node removenode = firstNode.next;
        firstNode.next.next.prev = firstNode;
        firstNode.next = firstNode.next.next;
        removenode.prev = null;
        removenode.next = null;
        size -= 1;
        return  item;
    }

    @Override
    public T removeLast() {
        if (size <= 0){return null;}
        T item = lastNode.prev.item;
        Node removenode = lastNode.prev;
        lastNode.prev.prev.next = lastNode;
        lastNode.prev = lastNode.prev.prev;
        removenode.prev = null;
        removenode.next = null;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 | index >= size){return null;}
        Node current = firstNode.next;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.item;
    }
    public Iterator<T> iterator(){
        return new LinkedListIterator<T>();
    }
    private class LinkedListIterator<T> implements Iterator{
        private Node current = firstNode;

        @Override
        public boolean hasNext() {
            return current.next != lastNode;
        }

        @Override
        public T next() {
            if (hasNext()) {
                current = current.next;
                return (T) current.next.item;
            }
            return null;
        }
    }
    public boolean equals(Object o){
        if (!(o instanceof Deque)){
            return false;
        }
        if (size != ((Deque<?>) o).size()){
            return false;
        }
        for (int i = 0; i < size; i++){
            if (get(i) != ((Deque<?>) o).get(i)){
                return false;
            }
        }
        return true;
    }
}
