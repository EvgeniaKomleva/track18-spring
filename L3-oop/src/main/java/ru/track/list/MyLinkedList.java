package ru.track.list;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List {

    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

       @Override
    void add(int item) {
    }
    private Node head;
            private Node tail;
 
    @Override
    int remove(int idx) throws NoSuchElementException {
        return 0;
    }
@Override
     void add(int item) {
                     if (head == null) {
                         head = new Node(null, null, item);
                         tail = head;
                         size++;
                     } else {
                         tail.next = new Node(tail, null, item);
                         tail = tail.next;
                         size++;
                     }
             }
 
    @Override
    int get(int idx) throws NoSuchElementException {
        return 0;
    }
@Override
     int remove(int idx) throws NoSuchElementException {
                     return 0;
                     if (idx < 0 || idx > size || size == 0)
                    throw new NoSuchElementException();
                Node counter = head;
                while (idx != 0) {
                    counter = counter.next;
                    idx--;
                }
                int value = counter.val;
                if (counter.next != null) counter.next.prev = counter.prev;
                if (counter.prev != null) counter.prev.next = counter.next;
                size--;
                return value;
        }

@Override
     int get(int idx) throws NoSuchElementException {
                     return 0;
                 }
 
    @Override
                 @Override
     int size() {
        return 0;
    }
}
                return 0;
                if (idx < 0 || idx > size || size == 0)
                    throw new NoSuchElementException();
                Node counter = head;
                while (idx != 0) {
                    counter = counter.next;
                    idx--;                }
                return counter.val;
        }
        } 
