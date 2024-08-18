import java.util.Iterator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyLinkedList<E extends Cloneable> implements Iterable<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    // Constructor to initialize an empty list
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add a new element to the end of the list
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

    // Get an element at a specified index
    public E get(int index) {
        checkIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    // Remove an element at a specified index
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            E value = head.getValue();
            head = head.getNext();
            size--;
            return value;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            Node<E> nodeToRemove = current.getNext();
            current.setNext(nodeToRemove.getNext());
            size--;
            return nodeToRemove.getValue();
        }
    }

    // Get the current size of the list
    public int size() {
        return size;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if the list contains a certain element
    public boolean contains(E e) {
        if (head == null) {
            return false;
        }
        return head.isContained(e);
    }

    // Clear all elements from the list
    public void clear() {
        head = null;
        size = 0;
    }

    // Clone the entire list
    public MyLinkedList<E> clone() {
        MyLinkedList<E> clonedList = new MyLinkedList<>();
        Node<E> current = head;
        while (current != null) {
            E clonedElement = cloneElement(current.getValue());
            clonedList.add(clonedElement);
            current = current.getNext();
        }
        return clonedList;
    }

    private E cloneElement(E element) {
        if (element == null) {
            return null;
        }
        try {
            Method cloneMethod = element.getClass().getMethod("clone");
            return (E) cloneMethod.invoke(element);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Cloning failed for element: " + element, e);
        }
    }

    // Check if the index is within bounds
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Convert list to string for display
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getValue());
            current = current.getNext();
            if (current != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<E> {
        private Node<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements");
            }
            E value = current.getValue();
            current = current.getNext();
            return value;
        }
    }
}
