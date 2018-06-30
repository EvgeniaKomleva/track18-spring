package ru.track.list;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 *
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {

   private final int INIT_SIZE = 4;
    private int[] array;
     public MyArrayList() {
 
        array = new int[INIT_SIZE];
        size = 0;
     }
 
     public MyArrayList(int capacity) {
 
        array = new int[capacity];
        size = capacity;
     }
 
     @Override
     void add(int item) {
 
        if (size < array.length) {
            array[size] = item;
            size++; }
            else {
            int[] newArray = new int[(array.length + 1) * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
            array[size] = item;
            size++; }
     }
 
     @Override
     int remove(int idx) throws NoSuchElementException {
        return 0;
                return 0;
                int elem = array[idx];
                for (int i = idx; i < size; i++) {
                        array[i] = array[i + 1];
                        array[i + 1] = 0;
                    }
                size--;
                return elem;
     }
 
     @Override
     int get(int idx) throws NoSuchElementException {
        return 0;
    }
               return 0;
            }
 
    @Override
           @Override
     int size() {
        return 0;
               return 0;
                if (idx < 0 || idx > size || size == 0){
                        throw new NoSuchElementException();
                    }
                return array[idx];
     }
}
