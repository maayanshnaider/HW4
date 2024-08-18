import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class MyLinkedList<E extends Cloneable> implements Iterable<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * add an element at the end of the list
     * if it's the first one, initialize it to be head and tail of the linked list
     * add to the list size +1
     * @param e
     */
    public void add(E e) {
        if (head == null) {
            head = new Node<>(e);
            tail = head;
        } else {
            tail.setNext(new Node<>(e));
            tail = tail.getNext();
        }
        size++;
    }

    /**
     * get value at a certain index of the linkedlist by going over the linked list until reaching the desired node
     * @param index
     * @return
     */
    public E get(int index) {
        checkIndex(index);
        Node<E> node_1 = head;
        for (int i = 0; i < index; i++) {
            node_1 = node_1.getNext();
        }
        return node_1.getValue();
    }

    /**
     * removing an element from start
     * reassign head
     * @return
     */
    public E remove() {
            E value = head.getValue();
            head = head.getNext();
            size--;
            return value;
        }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * we will use this to check if the group contains a friend
     * @param e
     * @return
     */
    public boolean contains(E e) {
        if (head == null) {
            return false;
        }
        return head.isContained(e);
    }

    /**
     * creating a deep copy of the queue
     * @return
     */
    @Override
    public MyLinkedList<E> clone() {
        MyLinkedList<E> clonedList = new MyLinkedList<>();
        Node<E> current_person = head;
        while (current_person != null) {
            E clonedElement = cloneElement(current_person.getValue());
            clonedList.add(clonedElement);
            current_person = current_person.getNext();
        }
        return clonedList;
    }

    /**
     * clones a person (deep copy)
     * @param element
     * @return
     */
    private E cloneElement(E element) {
        if (element == null) {
            return null;
        }
        try {
            Method cloneMethod = element.getClass().getMethod("clone");
            return (E) cloneMethod.invoke(element);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException();
        }
    }

    /**
     * checking if index is within the defined bounds
     * @param index
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

    /**
     * an iterator fo MyLinkedList
     */
    private class MyLinkedListIterator implements Iterator<E> {
        private Node<E> node_to_iterate = head;

        @Override
        public boolean hasNext() {
            return node_to_iterate != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }
            E value = node_to_iterate.getValue();
            node_to_iterate = node_to_iterate.getNext();
            return value;
        }
    }
}
